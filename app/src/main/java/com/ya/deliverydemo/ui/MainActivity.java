package com.ya.deliverydemo.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.ya.deliverydemo.ILoadInfoView;
import com.ya.deliverydemo.MainActivityPresenter;
import com.ya.deliverydemo.R;
import com.ya.deliverydemo.adapter.ExpressListAdapter;
import com.ya.deliverydemo.entity.ExpressList.ShowapiResBodyEntity.ExpressListEntity;
import com.ya.deliverydemo.ui.BaseActivity;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ILoadInfoView<List<ExpressListEntity>>, SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    private SuperRecyclerView mExpressLogoList;
    private SearchView mSearch;
    private List<ExpressListEntity> listEntities = new ArrayList<>();
    private ExpressListAdapter logoAdapter;
    private MainActivityPresenter presenter;
    private int page = 1;
    private boolean isLoadingMore = true;
    private SuperRecyclerView mExpressDescriList;
    private ExpressListAdapter descriAdapter;
    private ProgressWheel mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (listEntities.size() != 0)
                    presenter.flipit(mExpressLogoList, mExpressDescriList);
            }
        });
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        //测试
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("E140160995A5DC6C3FD145CAE844F794").build();
//        //加入你的設備代碼
////        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        //---------有米广告条--------
        // 获取要嵌入广告条的布局
        final LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);
        final LinearLayout advertisementLayout = (LinearLayout) findViewById(R.id.advertisement);



        mSearch = (SearchView) findViewById(R.id.search_express);
        mExpressLogoList = (SuperRecyclerView) findViewById(R.id.express_list_logo);
        mExpressDescriList = (SuperRecyclerView) findViewById(R.id.express_list_descri);
        mProgress = (ProgressWheel) findViewById(R.id.progress_wheel);

        //logo列表
        mExpressLogoList.setLayoutManager(new LinearLayoutManager(this));
        mExpressLogoList.setRefreshListener(this);
        mExpressLogoList.setOnMoreListener(this);
        mExpressLogoList.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        logoAdapter = new ExpressListAdapter(mContext, false, listEntities);
        mExpressLogoList.setAdapter(logoAdapter);

        //部分描述列表
        mExpressDescriList.setLayoutManager(new LinearLayoutManager(this));
        mExpressDescriList.setRefreshListener(this);
        mExpressDescriList.setOnMoreListener(this);
        mExpressDescriList.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        descriAdapter = new ExpressListAdapter(mContext, true, listEntities);
        mExpressDescriList.setAdapter(descriAdapter);
        // mExpressDescriList.setRotationY(-90f);

        //修改搜索字体
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) mSearch.findViewById(R.id.search_src_text);
        textView.setTextColor(getResources().getColor(android.R.color.holo_blue_light));
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(this.getClass().getName(), "newText:" + query);
                List<ExpressListEntity> data = presenter.doSearch(query);
                //logoAdapter.notifyDataSetChanged();
                Log.i(this.getClass().getName(), "size-->" + data.size());
                descriAdapter.refresh(data);
                mExpressDescriList.hideProgress();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d(this.getClass().getName(), "newText:" + newText);
                List<ExpressListEntity> data = presenter.doSearch(newText);
                //logoAdapter.notifyDataSetChanged();
                Log.i(this.getClass().getName(), "size-->" + data.size());
                descriAdapter.refresh(data);
                mExpressDescriList.hideProgress();
                return true;
            }
        });


        presenter = new MainActivityPresenter(this);
        presenter.loadIndo(page);
        try {
            presenter.addAdvertisement(this, bannerLayout,advertisementLayout);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void setData(List<ExpressListEntity> data) {
        if (isLoadingMore) {//防止搜索时加载下一页的数据
            if (page == 1) {
                listEntities.clear();
            }
            if (data != null) {
                listEntities.addAll(data);
                descriAdapter.notifyDataSetChanged();
            }
        } else {
            mExpressLogoList.hideMoreProgress();//使用的第三方控件照成的多判断
        }
        presenter.setTemporaryDataList(data);
    }

    @Override
    public void showError(int erroCode, String error) {
        showToastMsg(error);
    }

    @Override
    public void showBlueProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissBlueProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        page = 1;
        presenter.loadIndo(page);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        presenter.loadIndo(++page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_collection:
                startActivity(new Intent(this, CollectionActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
