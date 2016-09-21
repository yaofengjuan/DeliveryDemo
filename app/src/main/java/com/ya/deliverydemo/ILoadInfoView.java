package com.ya.deliverydemo;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public interface ILoadInfoView<T> {

    void setData(T data);

    void showError(int erroCode,String error);

    void showBlueProgress();

    void dismissBlueProgress();
}
