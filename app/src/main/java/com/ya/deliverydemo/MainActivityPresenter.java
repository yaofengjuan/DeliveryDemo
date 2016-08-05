package com.ya.deliverydemo;

import android.os.Handler;
import android.text.TextUtils;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.ya.deliverydemo.entity.ExpressList;
import com.ya.deliverydemo.entity.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public class MainActivityPresenter {
    private ILoadInfoView view;
    private MainActivityModel model;
    protected Handler mHandler = new Handler();
    private boolean isLoadingMore = true;

    public MainActivityPresenter(ILoadInfoView view) {
        this.view = view;
        model = new MainActivityModel();
    }

    public void loadIndo(final int page) {
        if (isLoadingMore) {
            new Thread() {
                //在新线程中发送网络请求
                public void run() {
                    final ExpressList list = model.loadInfo(page);
                    //把返回内容通过handler对象更新到界面
                    mHandler.post(new Thread() {
                        public void run() {
                            if (list.showapi_res_code == 0) {
                                if (list.showapi_res_body.ret_code == 0) {
                                    isLoadingMore = true;
                                    view.setData(list.showapi_res_body.expressList);
                                } else {
                                    isLoadingMore = false;
                                    view.showError(list.showapi_res_body.ret_code, list.showapi_res_body.msg);
                                }
                            } else {
                                view.showError(Constant.bodyError, list.showapi_res_error);
                            }
                        }
                    });
                }
            }.start();
        }

    }

    public void doSearch(List<ExpressListEntity> listEntities, String str) {
        if (!TextUtils.isEmpty(str)) {
            isLoadingMore = false;
            model.doSearch(listEntities, str);
        } else {
            isLoadingMore = true;
            listEntities.clear();
            listEntities.addAll(getTemporaryDataList());
        }
        //model.doSearch(listEntities, str);
    }

    /**
     * 获取在搜索前查看的数据
     *
     * @return
     */
    public List<ExpressListEntity> getTemporaryDataList() {
        return model.getTemporaryDataList();
    }


    public void flipit(SuperRecyclerView mLogoList, SuperRecyclerView mDescriList) {
        model.flipit(mLogoList, mDescriList);
    }
}
