package com.example.chenyuelun.myliangcang.base;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public interface BasePresenter {

    //获取数据
    void loadData();

    //刷新数据
    void onRefresh();


    RxFragment getFragment();
}
