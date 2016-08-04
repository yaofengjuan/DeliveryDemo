package com.ya.deliverydemo;

import android.os.Handler;

/**
 * Created by YaoFengjuan on 2016/6/22.
 */
public class QueryActivityPresenter {
    private QueryActivityModel model;
    private ILoadInfoView view;
    protected Handler mHandler = new Handler();

    public QueryActivityPresenter(ILoadInfoView view) {
        this.view = view;
        model = new QueryActivityModel();
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
}
