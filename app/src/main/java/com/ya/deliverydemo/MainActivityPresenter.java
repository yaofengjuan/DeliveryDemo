package com.ya.deliverydemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.ya.deliverydemo.entity.ExpressList;
import com.ya.deliverydemo.entity.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import net.youmi.android.normal.banner.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ya.deliverydemo.R.id.delete;

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
        if (page == 1) {
            view.showBlueProgress();
        }
        if (isLoadingMore) {
            new Thread() {
                //在新线程中发送网络请求
                public void run() {
                    final ExpressList list = model.loadInfo(page);
                    if (list != null) {
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

                                if (page == 1) {
                                    view.dismissBlueProgress();
                                }
                            }
                        });
                    }
                }
            }.start();
        }

    }

    public void setTemporaryDataList(List<ExpressListEntity> temporaryDataList) {
        this.model.setTemporaryDataList(temporaryDataList);
    }

    public List<ExpressListEntity> doSearch(String str) {
        if (!TextUtils.isEmpty(str)) {
            isLoadingMore = false;
            Log.i("LOG", "dosearch");
            return model.doSearch(str);
        } else {
            isLoadingMore = true;
            return getTemporaryDataList();
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

    /**
     * 添加广告view
     *
     * @param context
     * @param bannerLayout
     * @param advertisementLayout
     */
    public void addAdvertisement(final Context context, final LinearLayout bannerLayout, final LinearLayout advertisementLayout) throws ParseException {
        final String dateStr = "date";
        final SharedPreferences sp = context.getSharedPreferences(Constant.AdverSevenDate, context.MODE_PRIVATE);
        if (sp.contains(dateStr)) {
            String date = sp.getString(dateStr, "");
            Log.i(this.getClass().getName(), "date--->" + date);
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat sdr = new SimpleDateFormat("yyyy-mm-dd hh:mm");
            Date old = sdr.parse(date);
            long diff = now.getTime() - old.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            if (days <= 7) {  //小于7天，广告部显示
                return;
            }
        }

        final TextView delete = (TextView) advertisementLayout.findViewById(R.id.delete);
        // 获取广告条
        View bannerView = BannerManager.getInstance(context)
                .getBannerView(new net.youmi.android.normal.banner.BannerViewListener() {
                    @Override
                    public void onRequestSuccess() {
                        delete.setVisibility(View.VISIBLE);
                        bannerLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSwitchBanner() {

                    }

                    @Override
                    public void onRequestFailed() {
                        bannerLayout.setVisibility(View.GONE);
                        delete.setVisibility(View.GONE);
                    }
                });


        // 将广告条加入到布局中
        bannerLayout.addView(bannerView);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bannerLayout.removeAllViews();
                advertisementLayout.setVisibility(View.GONE);
                SharedPreferences.Editor editor = sp.edit();
                SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdr.format(new java.util.Date());
                editor.putString(dateStr, date);
                editor.commit();
            }
        });
    }
}
