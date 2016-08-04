package com.ya.deliverydemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity implements ILoadInfoView<List<ExpressInfo>> {

    private RecyclerView myCollection;
    private List<ExpressInfo> list = new ArrayList<>();
    private ExpressInfoAdapter adapter;
    private CollectionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCollection = (RecyclerView) findViewById(R.id.my_collection);
        myCollection.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpressInfoAdapter(this, list);
        myCollection.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        presenter = new CollectionPresenter(this, this);
        presenter.load();
    }


    @Override
    public void setData(List<ExpressInfo> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(int erroCode, String error) {
        showToastMsg(error);
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
