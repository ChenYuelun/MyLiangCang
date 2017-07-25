package com.example.chenyuelun.myliangcang.view.store;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.model.entity.StoreTypeBean;
import com.example.chenyuelun.myliangcang.presenter.StoreTypePresnter;
import com.example.chenyuelun.myliangcang.presenter.adapter.TypeRvAdapter;
import com.example.chenyuelun.myliangcang.presenter.listener.OnItemClickListener;
import com.example.chenyuelun.myliangcang.utils.UiUtil;

import butterknife.BindView;


/**
 * Created by chenyuelun on 2017/7/24.
 */

public class TypeFragment extends BaseFragment implements TypeView {
    @BindView(R.id.mRecyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TypeRvAdapter typeRvAdapter;
    private StoreTypePresnter storeTypePresnter;

    public static TypeFragment newIntance() {
        return new TypeFragment();
    }

    @Override
    protected int getLayoutId() {
        storeTypePresnter = new StoreTypePresnter(this);
        return R.layout.fragment_type_store;
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        isPrepared = true;
        initRecyclerView();
        initRefreshLayout();
        if(isPrepared && isVisible) {
            storeTypePresnter.loadData(this);
        }
    }

    @Override
    protected void onVisible() {
        if(!isPrepared) {
           return;
        }
        storeTypePresnter.loadData(this);
    }

    //配置recyclerview
    @Override
    protected void initRecyclerView() {
        typeRvAdapter = new TypeRvAdapter(getContext());
        recyclerview.setAdapter(typeRvAdapter);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorBlack);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void finishTask(Object object) {
        StoreTypeBean storeTypeBean = (StoreTypeBean) object;
        mSwipeRefreshLayout.setRefreshing(false);
        typeRvAdapter.refresh(storeTypeBean);
    }

    @Override
    protected void initListener() {
        super.initListener();
        //下拉刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                storeTypePresnter.onRefresh(TypeFragment.this);
            }
        });


        typeRvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                StoreTypeBean.DataBean.ItemsBean item = typeRvAdapter.getItem(position);
                UiUtil.showToast(item.getCat_name());
            }
        });
    }
}
