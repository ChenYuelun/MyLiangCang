package com.example.chenyuelun.myliangcang.presenter;

import com.example.chenyuelun.myliangcang.model.store.IStoreModel;
import com.example.chenyuelun.myliangcang.model.store.StoreTypeModel;
import com.example.chenyuelun.myliangcang.presenter.ipresenter.IStorePresenter;
import com.example.chenyuelun.myliangcang.view.store.TypeView;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class StoreTypePresnter extends IStorePresenter {

    private IStoreModel iStoreTypeModel;
    private TypeView typeView;
    private RxFragment fragment;

    public StoreTypePresnter(RxFragment fragment){
        this.iStoreTypeModel = new StoreTypeModel(this);
        this.typeView = (TypeView) fragment;
        this.fragment = fragment;
    }
    @Override
    public void loadData(){
        iStoreTypeModel.loadData();
    }

    @Override
    public void onRefresh(){
        loadData();
    }



    @Override
    public void setData(Object object){
        typeView.finishTask(object);
    }

    @Override
    public RxFragment getFragment() {
        return fragment;
    }

}
