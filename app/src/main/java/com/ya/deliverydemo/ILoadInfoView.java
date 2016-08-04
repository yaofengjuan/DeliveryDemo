package com.ya.deliverydemo;

import com.ya.deliverydemo.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public interface ILoadInfoView<T> {

    void setData(T data);

    void showError(int erroCode,String error);
}
