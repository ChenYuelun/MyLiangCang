package com.example.chenyuelun.myliangcang.model.store;

import android.util.Log;

import com.example.chenyuelun.myliangcang.commen.network.RetrofitHelper;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiConstants;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiService;
import com.example.chenyuelun.myliangcang.model.entity.StoreTypeBean;
import com.example.chenyuelun.myliangcang.presenter.StoreTypePresnter;
import com.example.chenyuelun.myliangcang.utils.UiUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class StoreTypeModel implements IStoreModel {

    private final StoreTypePresnter presnter;

    public StoreTypeModel(StoreTypePresnter presnter){
        this.presnter = presnter;
    }
    @Override
    public void loadData() {
        RetrofitHelper.createApi(ApiService.class, ApiConstants.STORE_BASEURL)
                .getStoreType()
                .compose(presnter.getFragment().<StoreTypeBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreTypeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TAG", "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull StoreTypeBean storeTypeBean) {
                        Log.e("TAG", "onNext");
                        presnter.setData(storeTypeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG" + "Error", e.getMessage());
                        UiUtil.showToast("获取数据失败，请检查您的网络");

                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete");
                    }
                });
    }


}
