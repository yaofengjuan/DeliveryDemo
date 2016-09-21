package com.ya.deliverydemo;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.show.api.ShowApiRequest;
import com.ya.deliverydemo.entity.ExpressList;
import com.ya.deliverydemo.entity.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public class MainActivityModel implements ILoadPageModel<ExpressList> {

    @Override
    public ExpressList loadInfo(int page) {
        try {
            String result = new ShowApiRequest(Constant.BASE_URL + "20", Constant.appid, Constant.secret).addTextPara("expName", "")
                    .addTextPara("maxSize", "250")
                    .addTextPara("page", String.valueOf(page)).post();
            Log.d(this.getClass().getName(), "page==" + page);
            Log.d(this.getClass().getName(), "result:->->->->->" + result);
            ExpressList list = ExpressList.objectFromData(result);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void doSearch(List<ExpressListEntity> listEntities, String str) {
        temporaryDataList.clear();
        temporaryDataList.addAll(listEntities);

        List<ExpressListEntity> temporary = new ArrayList<>();//可适配的数据
        for (ExpressListEntity entity : listEntities) {
            if (entity.expName.contains(str)) {
                temporary.add(entity);
            }
        }
        if (temporary.size() > 0) {
            listEntities.clear();
            listEntities.addAll(temporary);
        }
    }


    private List<ExpressListEntity> temporaryDataList = new ArrayList<>();//暂存所有

    public List<ExpressListEntity> getTemporaryDataList() {
        return temporaryDataList;
    }

    private Interpolator accelerator = new AccelerateInterpolator();
    private Interpolator decelerator = new DecelerateInterpolator();

    public void flipit(SuperRecyclerView mLogoList, SuperRecyclerView mDescriList) {
        final SuperRecyclerView visibleList;
        final SuperRecyclerView invisibleList;
        if (mLogoList.getVisibility() == View.GONE) {
            visibleList = mDescriList;
            invisibleList = mLogoList;
        } else {
            invisibleList = mDescriList;
            visibleList = mLogoList;
        }
        int position = ((LinearLayoutManager) visibleList.getRecyclerView().getLayoutManager()).findLastVisibleItemPosition();
        Log.d("TAG", "position:" + position);
        invisibleList.getRecyclerView().smoothScrollToPosition(position);

        ObjectAnimator visToInvis = ObjectAnimator.ofFloat(visibleList, "rotationY", 0f, 90f);
        visToInvis.setDuration(500);
        visToInvis.setInterpolator(accelerator);
        final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(invisibleList, "rotationY",
                -90f, 0f);
        invisToVis.setDuration(500);
        invisToVis.setInterpolator(decelerator);
        visToInvis.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {
                visibleList.setVisibility(View.GONE);
                invisToVis.start();
                invisibleList.setVisibility(View.VISIBLE);

            }
        });
        visToInvis.start();


    }
}
