package com.example.chenyuelun.myliangcang.model.store;

import com.example.chenyuelun.myliangcang.presenter.StoreTypePresnter;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public interface IStoreTypeModel {
    void loadData(StoreTypePresnter presnter, RxFragment fragment);
}
