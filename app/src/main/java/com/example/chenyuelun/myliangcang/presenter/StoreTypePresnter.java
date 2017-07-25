package com.example.chenyuelun.myliangcang.presenter;

import com.example.chenyuelun.myliangcang.model.entity.StoreTypeBean;
import com.example.chenyuelun.myliangcang.model.store.IStoreTypeModel;
import com.example.chenyuelun.myliangcang.model.store.IStoreTypeModelImpl;
import com.example.chenyuelun.myliangcang.view.store.TypeView;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class StoreTypePresnter {
    private IStoreTypeModel iStoreTypeModel;
    private TypeView typeView;

    public StoreTypePresnter(TypeView typeView){
        this.iStoreTypeModel = new IStoreTypeModelImpl();
        this.typeView = typeView;
    }

    public void loadData(RxFragment fragment){
        iStoreTypeModel.loadData(this,fragment);
    }

    public void onRefresh(RxFragment fragment){
        loadData(fragment);
    }

    public void setData(StoreTypeBean storeTypeBean){
        typeView.finishTask(storeTypeBean);
    }

}
