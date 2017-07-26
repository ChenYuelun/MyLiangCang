package com.example.chenyuelun.myliangcang.presenter;

import com.example.chenyuelun.myliangcang.model.store.IStoreModel;
import com.example.chenyuelun.myliangcang.model.store.StoreHomeModel;
import com.example.chenyuelun.myliangcang.presenter.ipresenter.IStorePresenter;
import com.example.chenyuelun.myliangcang.view.store.TypeView;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/26.
 */

public class StoreHomePresenter extends IStorePresenter {
    private final RxFragment fragment;
    private final TypeView typeView;
    private final IStoreModel iStoreModel;

    public StoreHomePresenter(RxFragment fragment){
        this.fragment= fragment;
        this.typeView = (TypeView) fragment;
        this.iStoreModel = new StoreHomeModel(this);
    }
    @Override
    public void loadData() {
        iStoreModel.loadData();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public RxFragment getFragment() {
        return fragment;
    }

    @Override
    public void setData(Object object) {
       typeView.finishTask(object);
    }
}
