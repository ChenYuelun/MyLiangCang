package com.example.chenyuelun.myliangcang.view.store;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.model.entity.StoreHomeBean;
import com.example.chenyuelun.myliangcang.presenter.StoreHomePresenter;
import com.example.chenyuelun.myliangcang.presenter.adapter.HomeRvAdapter;

import butterknife.BindView;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class HomeFragment extends BaseFragment implements TypeView {
    @BindView(R.id.mRecyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private StoreHomePresenter storeHomePresenter;
    private HomeRvAdapter homeRvAdapter;

    public static HomeFragment newIntance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        storeHomePresenter = new StoreHomePresenter(this);
        return R.layout.refresh_recyclerview;
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        isPrepared = true;
        initRecyclerView();
        initRefreshLayout(mSwipeRefreshLayout);
        Log.e("TAG", "HomeFragment开始联网finishCreateView ==" + isVisible);

    }

    @Override
    protected void onVisible() {
        if(isPrepared) {
            Log.e("TAG", "HomeFragment开始联网onVisible");
            storeHomePresenter.loadData();
        }
    }

    @Override
    protected void initRecyclerView() {
        homeRvAdapter = new HomeRvAdapter(getContext());
        mRecyclerview.setAdapter(homeRvAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false));
    }


    @Override
    public void finishTask(Object object) {
        StoreHomeBean storeHomeBean = (StoreHomeBean) object;
        StoreHomeBean.DataBean.ItemsBean items = storeHomeBean.getData().getItems();
        homeRvAdapter.refresh(items.getList());
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
