package com.example.chenyuelun.myliangcang.model.store;

import android.util.Log;

import com.example.chenyuelun.myliangcang.commen.network.RetrofitHelper;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiConstants;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiService;
import com.example.chenyuelun.myliangcang.model.entity.StoreHomeBean;
import com.example.chenyuelun.myliangcang.presenter.StoreHomePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenyuelun on 2017/7/26.
 */

public class StoreHomeModel implements IStoreModel {


    private final StoreHomePresenter presenter;

    public StoreHomeModel(StoreHomePresenter presenter) {
       this.presenter = presenter;
    }

    @Override
    public void loadData() {
        RetrofitHelper.createApi(ApiService.class, ApiConstants.HOME_BASEURL)
                .getStoreHome()
                .compose(presenter.getFragment().<StoreHomeBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreHomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TAG", "StoreHomeModel--onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull StoreHomeBean storeHomeBean) {
                        Log.e("TAG", "StoreHomeModel--onNext---"+storeHomeBean.getData().getItems().getList().get(0).getContent_type());

                        presenter.setData(storeHomeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "StoreHomeModel--onNext");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "StoreHomeModel--onComplete");
                    }
                });
    }
}
