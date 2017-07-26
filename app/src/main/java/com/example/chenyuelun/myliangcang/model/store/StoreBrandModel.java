package com.example.chenyuelun.myliangcang.model.store;

import android.util.Log;

import com.example.chenyuelun.myliangcang.commen.network.RetrofitHelper;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiConstants;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiService;
import com.example.chenyuelun.myliangcang.model.entity.StoreBrandBean;
import com.example.chenyuelun.myliangcang.presenter.StoreBrandPresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class StoreBrandModel implements IStoreModel {
    private final StoreBrandPresenter presenter;

    public StoreBrandModel(StoreBrandPresenter storeBrandPresenter) {
        this.presenter = storeBrandPresenter;
    }

    @Override
    public void loadData() {
        int page = presenter.getPager();
        RetrofitHelper.createApi(ApiService.class, ApiConstants.BRAND_BASEURL)
                .getStoreBrand("Android",20,page,"430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768","1.0")
                .compose(presenter.getFragment().<StoreBrandBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoreBrandBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TAG", "StoreBrandModel -- onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull StoreBrandBean storeBrandBean) {
                        Log.e("TAG", "StoreBrandModel -- onNext==" + storeBrandBean.getData().getNum_items());
                        presenter.setData(storeBrandBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "StoreBrandModel -- onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "StoreBrandModel -- onComplete");
                    }
                });
    }



}
