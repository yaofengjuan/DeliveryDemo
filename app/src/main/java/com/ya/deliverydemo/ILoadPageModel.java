package com.ya.deliverydemo;

/**
 * Created by YaoFengjuan on 2016/6/14.
 */
public interface ILoadPageModel<T> {
    T loadInfo(int page);
}
