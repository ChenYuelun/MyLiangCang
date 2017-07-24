package com.example.chenyuelun.myliangcang.view.store;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class StoreFragment extends BaseFragment {
    @BindView(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_cart)
    ImageView ivTitleCart;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp_store)
    ViewPager vpStore;

    String[] titles = {"分类", "品牌", "首页","专题", "礼物"};

    private List<BaseFragment> fragments;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public static StoreFragment newIntance() {
        return new StoreFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        isPrepared = true;
        initFragments();
        initViewPager();
        finishTask();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(TypeFragment.newIntance());
        fragments.add(BrandFragment.newIntance());
        fragments.add(HomeFragment.newIntance());
        fragments.add(TopicFragment.newIntance());
        fragments.add(GiftFragment.newIntance());
    }

    private void initViewPager() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        vpStore.setAdapter(myFragmentPagerAdapter);
        tablayout.setupWithViewPager(vpStore);
    }


    @Override
    protected void finishTask() {
        //vpStore.setCurrentItem(2);

    }

    @Override
    protected void initTitle() {
        super.initTitle();
        Log.e("TAG", "initTitle");
        super.initTitle();
        tvTitle.setText("商店");
        ivTitleSearch.setVisibility(View.VISIBLE);
        ivTitleCart.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.iv_title_search, R.id.iv_title_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_search:
                UiUtils.showToast("搜索");
                break;
            case R.id.iv_title_cart:
                UiUtils.showToast("购物车");
                break;
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
