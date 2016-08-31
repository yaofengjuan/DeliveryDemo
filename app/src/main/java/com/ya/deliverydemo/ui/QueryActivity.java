package com.ya.deliverydemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ya.deliverydemo.Constant;
import com.ya.deliverydemo.ILoadInfoView;
import com.ya.deliverydemo.QueryActivityPresenter;
import com.ya.deliverydemo.R;
import com.ya.deliverydemo.adapter.ExpressContentAdapter;
import com.ya.deliverydemo.entity.ExpressContent;
import com.ya.deliverydemo.ui.BaseActivity;

public class QueryActivity extends BaseActivity implements ILoadInfoView<ExpressContent> {

    private SearchView mSearchExpress;
    private RecyclerView mExpressContent;
    private String simpleName;
    private QueryActivityPresenter presenter;
    private ExpressContentAdapter adapter;
    private TextView errorView;
    private String expName;
    private String logoUrl;
    private String mailNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new QueryActivityPresenter(this);
        initIntent();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        mSearchExpress = (SearchView) findViewById(R.id.search_express);
        mExpressContent = (RecyclerView) findViewById(R.id.express_content);
        errorView = (TextView) findViewById(R.id.erro_msg);
        mExpressContent.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpressContentAdapter(mContext);
        mExpressContent.setAdapter(adapter);

        mSearchExpress.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String mailNo = query;
                if (!TextUtils.isEmpty(mailNo)) {
                    presenter.loadInfo(simpleName, mailNo);
                } else {
                    showToastMsg("请输入订单号");
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.refresh(null);
                    if (errorView.isShown()) {
                        errorView.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });

    }


    private void initIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("simpleName")) {
            simpleName = intent.getStringExtra("simpleName");
        }
        if (intent.hasExtra("expName")) {
            expName = intent.getStringExtra("expName");
            getSupportActionBar().setTitle(expName);
        }

        if (intent.hasExtra("logoUrl")) {
            logoUrl = intent.getStringExtra("logoUrl");
        }

        if (intent.hasExtra("mailNo")) {
            mailNo = intent.getStringExtra("mailNo");
            if (!TextUtils.isEmpty(mailNo)) {
                presenter.loadInfo(simpleName, mailNo);
            }
        }
    }

    @Override
    public void setData(ExpressContent data) {
        if (errorView.getVisibility() == View.VISIBLE) {
            errorView.setVisibility(View.GONE);
        }
        adapter.refresh(data);
    }

    @Override
    public void showError(int erroCode, String error) {
        if (erroCode == Constant.bodyError) {
            errorView.setVisibility(View.GONE);
            showToastMsg(error);
        } else {
            switch (erroCode) {
                case 0:
                    errorView.setText("查询异常");
                    break;
                case 1:
                    errorView.setText("暂无记录");
                    break;
            }
            errorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
