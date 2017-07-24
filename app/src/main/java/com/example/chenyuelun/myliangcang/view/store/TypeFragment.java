package com.example.chenyuelun.myliangcang.view.store;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.commen.network.RetrofitHelper;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiConstants;
import com.example.chenyuelun.myliangcang.commen.network.api.ApiService;
import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by chenyuelun on 2017/7/24.
 */

public class TypeFragment extends BaseFragment {
    @BindView(R.id.mRecyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TypeRvAdapter typeRvAdapter;

    public static TypeFragment newIntance() {
        return new TypeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type_store;
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        isPrepared = true;
        initRecyclerView();
        lazyLoad();
    }

    //配置recyclerview
    @Override
    protected void initRecyclerView() {
        typeRvAdapter = new TypeRvAdapter(getContext());
        recyclerview.setAdapter(typeRvAdapter);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        initRefreshLayout();
    }


    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorBlack);
        loadData();
    }

    @Override
    protected void loadData() {
        RetrofitHelper.createApi(ApiService.class, ApiConstants.TYPE_BASEURL)
                .getStoreType()
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
                        
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG" +"Error", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete");
                    }
                });
    }
}
