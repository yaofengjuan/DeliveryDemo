package com.ya.deliverydemo;

import android.content.Context;
import android.os.Handler;

import com.ya.deliverydemo.entity.ExpressInfo;

import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/24.
 */
public class CollectionPresenter {


    private final CollectionModel model;
    private final SqlDB db;
    private ILoadInfoView view;
    private Handler mHandler;

    public CollectionPresenter(Context context, ILoadInfoView view) {
        this.view = view;
        model = new CollectionModel();
        db = SqlDB.getInstant(context);
    }

    public ExpressInfo update(ExpressInfo old) {
        return model.update(old);
    }

    public void updateMyLocalOrder(String mailNo, String lastMsg) {
        db.updateOrderLastMsg(mailNo, lastMsg);
    }

    public void load() {
        view.setData(loadFromLocal());
    }

    protected List<ExpressInfo> loadFromLocal() {
        return db.getMyOrder();
    }

    /**
     * 删除收藏的快递单信息
     */
    public void deleteMyOrder(String mailNo) {
        db.deleteMyOrder(mailNo);
    }
}
