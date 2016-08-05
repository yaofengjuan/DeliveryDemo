package com.ya.deliverydemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ya.deliverydemo.entity.ExpressInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 双重锁定
 * Created by YaoFengjuan on 2016/6/24.
 */
public class SqlDB {

    private volatile static SqlDB sqlDB;
    private final SQLiteDatabase db;


    private SqlDB(Context context) {
        DBUtils helper = new DBUtils(context, "express.db");
        db = helper.getReadableDatabase();
    }

    public static SqlDB getInstant(Context context) {

        if (sqlDB == null) {
            sqlDB = new SqlDB(context);
        }
        return sqlDB;
    }

    public void saveMyOrder(ExpressInfo myOrder) {
        String sql = "select * from myExp where (mailNo=" + myOrder.getMailNo() + ")";
        Cursor c = db.rawQuery(sql, null);
        if (!c.moveToNext()) {
            db.execSQL("insert into myExp(null,?,?,?,?,?,?)", new Object[]{myOrder.getExpSpellName(), myOrder.getMailNo(), myOrder.getSimpleName(), myOrder.getLogoUrl(), myOrder.getLastMsg(), myOrder.getRemarkName()});
        }
        c.close();

    }

    public void deleteMyOrder(String mailNo) {
        db.execSQL("delete from myExp where (mailNo=" + mailNo + ")");
    }

    public List<ExpressInfo> getMyOrder() {
        List<ExpressInfo> data = new ArrayList<>();
        String sql = "select * from myExp";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToNext()) {
            String logoUrl = c.getString(c.getColumnIndex("logoUrl"));
            String lastMsg = c.getString(c.getColumnIndex("lastMsg"));
            String expSpellName = c.getString(c.getColumnIndex("expSpellName"));
            String mailNo = c.getString(c.getColumnIndex("mailNo"));
            String remarkName = c.getString(c.getColumnIndex("remarkName"));
            String simpleName = c.getString(c.getColumnIndex("simpleName"));
            ExpressInfo order = new ExpressInfo(expSpellName, mailNo, simpleName, logoUrl, lastMsg, remarkName);
            data.add(order);
        }
        return data;
    }

    public void updateOrderLastMsg(String mailNo, String lastMsg) {
        db.execSQL("update myExp set lastMsg=" + lastMsg + "where mailNo=" + mailNo);
    }

}
