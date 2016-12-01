package com.ya.deliverydemo.delivery;

import android.app.Application;

import com.ya.deliverydemo.Constant;

import net.youmi.android.AdManager;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(
//                                Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(
//                                Stetho.defaultInspectorModulesProvider(this))
//                        .build());
        initYoumi();
    }

    /**
     * 初始化有米广告条
     */
    private void initYoumi() {

        AdManager.getInstance(this).init(Constant.YoumiAppId, Constant.YoumiAppSecret,
                false, true);
    }
}