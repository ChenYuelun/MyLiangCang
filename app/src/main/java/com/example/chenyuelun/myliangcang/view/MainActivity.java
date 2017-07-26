package com.example.chenyuelun.myliangcang.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.base.BaseActivity;
import com.example.chenyuelun.myliangcang.base.BaseFragment;
import com.example.chenyuelun.myliangcang.view.daren.DarenFragment;
import com.example.chenyuelun.myliangcang.view.magazine.MagazineFragment;
import com.example.chenyuelun.myliangcang.view.self.SelfFragment;
import com.example.chenyuelun.myliangcang.view.share.ShareFragment;
import com.example.chenyuelun.myliangcang.view.store.StoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    //用来盛放所有要显示的fragment
    private List<BaseFragment> fragments;

    //用来临时记录radiogroup选择第几个
    private int position;

    //用来记录上一次显示的fragment(切换之前的)，当要显示的与之前显示的不同才切换
    private BaseFragment preFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initFragmets();
        rgMain.setOnCheckedChangeListener(this);
        finishTask();
    }


    @Override
    public void finishTask() {
        super.finishTask();
        rgMain.check(R.id.rb_main_store);
    }

    //创建fragment集合 添加fragment
    private void initFragmets() {
        fragments = new ArrayList<>();
        fragments.add(StoreFragment.newIntance());
        fragments.add(MagazineFragment.newIntance());
        fragments.add(DarenFragment.newIntance());
        fragments.add(ShareFragment.newIntance());
        fragments.add(SelfFragment.newIntance());
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rb_main_store:
                position = 0;
                break;
            case R.id.rb_main_magazine:
                position = 1;
                break;
            case R.id.rb_main_daren:
                position = 2;
                break;
            case R.id.rb_main_share:
                position = 3;
                break;
            case R.id.rb_main_selef:
                position = 4;
                break;
        }

        switchFragment(position);
    }

    private void switchFragment(int position) {
        BaseFragment currentFragment = fragments.get(position);
        if(currentFragment != preFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(preFragment != null) {
                ft.hide(preFragment);
            }
            if(!currentFragment.isAdded()) {
                ft.add(R.id.fl_main,currentFragment);
            }else {
                ft.show(currentFragment);
            }
            ft.commit();
            preFragment = currentFragment;
        }
    }


    public static void start(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);;
    }
}
