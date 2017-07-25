package com.example.chenyuelun.myliangcang.model.store;

import android.util.Log;

import com.example.chenyuelun.myliangcang.commen.network.RetrofitHelper;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiConstants;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiService;
import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;
import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class IStoreTypeModelImpl implements IStoreTypeModel {

    @Override
    public void loadData(RxFragment context, final OnLodaDataListener listener) {
        RetrofitHelper.createApi(ApiService.class, ApiConstants.TYPE_BASEURL)
                .getStoreType()
                .compose(context.<StoreTypeBean>bindToLifecycle())
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
                        listener.onSuccess(storeTypeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG" + "Error", e.getMessage());
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete");
                    }
                });
    }
}
