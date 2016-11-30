package com.ya.deliverydemo;

import android.os.Handler;
import android.util.Log;

import com.ya.deliverydemo.entity.ExpressContent;
import com.ya.deliverydemo.entity.ExpressInfo;
import com.ya.deliverydemo.ui.BaseActivity;

/**
 * Created by YaoFengjuan on 2016/6/22.
 */
public class QueryActivityPresenter {
    private final SqlDB db;
    private QueryActivityModel model;
    private ILoadInfoView view;
    protected Handler mHandler = new Handler();

    public QueryActivityPresenter(ILoadInfoView view) {
        this.view = view;
        model = new QueryActivityModel();
        db = SqlDB.getInstant((BaseActivity) view);
    }

    public void loadInfo(final String simpleName, final String mailNo) {


        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                model.setParameter(simpleName, mailNo);
                final ExpressContent content = model.loadInfo();
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        if (content.showapi_res_code == 0) {
                            if (content.showapi_res_body.ret_code == 0) {
                                if (content.showapi_res_body.status > 1) {
                                    view.setData(content);
                                } else {
                                    view.showError(content.showapi_res_body.status, "");
                                }
                            } else {
                                view.showError(content.showapi_res_body.ret_code, content.showapi_res_body.msg);
                            }

                        } else {
                            view.showError(Constant.bodyError, content.showapi_res_error);
                        }
                    }
                });
            }
        }.start();
    }

    public void saveExpressInfo(String logoUrl, String expName, String simpleName, String mailNo) {
        ExpressInfo info = new ExpressInfo(expName, mailNo, simpleName, logoUrl, "", "");
        Log.i(this.getClass().getName(), "收藏快递信息--》" + info.toString());
        db.saveMyOrder(info);
    }

}
