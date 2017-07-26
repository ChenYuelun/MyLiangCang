package com.example.chenyuelun.myliangcang.view.store;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.model.entity.StoreBrandBean;
import com.example.chenyuelun.myliangcang.presenter.StoreBrandPresenter;
import com.example.chenyuelun.myliangcang.presenter.adapter.BrandLvAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class BrandFragment extends BaseFragment implements TypeView {
    @BindView(R.id.lv_brand)
    ListView lvBrand;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private BrandLvAdapter brandLvAdapter;
    private StoreBrandPresenter storeBrandPresenter;
    private boolean isLoadMore = false;

    public static BrandFragment newIntance() {
        return new BrandFragment();
    }

    @Override
    protected int getLayoutId() {
        storeBrandPresenter = new StoreBrandPresenter(this);
        return R.layout.fragment_brand_store;
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        isPrepared = true;
        initRecyclerView();
        initRefreshLayout(mSwipeRefreshLayout);
        if (isVisible) {
            storeBrandPresenter.loadData();
        }
    }

    @Override
    protected void onVisible() {
        if (!isPrepared) {
            return;
        }
        storeBrandPresenter.loadData();
    }


    @Override
    protected void initRecyclerView() {
        brandLvAdapter = new BrandLvAdapter(getContext());
        lvBrand.setAdapter(brandLvAdapter);
    }


    @Override
    public void finishTask(Object object) {
        StoreBrandBean storeBrandBean = (StoreBrandBean) object;
        List<StoreBrandBean.DataBean.ItemsBean> items = storeBrandBean.getData().getItems();
        brandLvAdapter.refresh(items,isLoadMore);
        mSwipeRefreshLayout.setRefreshing(false);
        isLoadMore = false;
    }

    @Override
    protected void initListener() {
        lvBrand.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(lvBrand.getCount()-1 == lvBrand.getLastVisiblePosition()) {
                    storeBrandPresenter.loadMore();
                    isLoadMore = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                storeBrandPresenter.loadData();
            }
        });

    }
}
