package com.example.chenyuelun.myliangcang.presenter;

import com.example.chenyuelun.myliangcang.model.entity.StoreBrandBean;
import com.example.chenyuelun.myliangcang.model.store.IStoreModel;
import com.example.chenyuelun.myliangcang.model.store.StoreBrandModel;
import com.example.chenyuelun.myliangcang.presenter.ipresenter.IStorePresenter;
import com.example.chenyuelun.myliangcang.view.store.TypeView;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class StoreBrandPresenter extends IStorePresenter {

    public IStoreModel iStoreModel;
    private TypeView typeView;
    private int page = 1;

    public RxFragment getFragment() {
        return fragment;
    }

    private RxFragment fragment;


    public StoreBrandPresenter(RxFragment fragment){
        this.iStoreModel = new StoreBrandModel(this);
        this.typeView = (TypeView) fragment;
        this.fragment = fragment;
    }
    @Override
    public void loadData() {
        iStoreModel.loadData();
    }

    @Override
    public void onRefresh() {
        page = 1;
        loadData();
    }

    @Override
    public void loadMore() {
        page++;
        iStoreModel.loadData();
    }

    @Override
    public void setData(Object object) {
        typeView.finishTask(object);
    }

    public int getPager() {
        return page;
    }
}
