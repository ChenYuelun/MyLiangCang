package com.example.chenyuelun.myliangcang.presenter;

import android.util.Log;

import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;
import com.example.chenyuelun.myliangcang.model.store.IStoreTypeModel;
import com.example.chenyuelun.myliangcang.model.store.IStoreTypeModelImpl;
import com.example.chenyuelun.myliangcang.model.store.OnLodaDataListener;
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
        iStoreTypeModel.loadData(fragment,new OnLodaDataListener() {
            @Override
            public void onSuccess(StoreTypeBean storeTypeBean) {
                typeView.finishTask(storeTypeBean);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", e.getMessage());
            }
        });
    }

    public void onRefresh(RxFragment fragment){
        loadData(fragment);
    }

}
