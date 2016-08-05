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
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                final List<ExpressInfo> list = loadFromLocal();
                for (ExpressInfo temprory : list) {
                    final ExpressInfo info = update(temprory);//获取最新订单详情
                    updateMyLocalOrder(info.getMailNo(), info.getLastMsg());//更新收藏的订单
                }
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        view.setData(loadFromLocal());
                    }
                });

            }
        }.start();
    }

    protected List<ExpressInfo> loadFromLocal() {
        return db.getMyOrder();
    }
}
