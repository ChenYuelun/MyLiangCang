package com.example.chenyuelun.myliangcang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //初始化黄油刀绑定框架
        bind = ButterKnife.bind(this);
        //初始化控价
        initViews(savedInstanceState);

    }
    //设置布局
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initViews(Bundle savedInstanceState);



    /**
     * 设置数据显示
     */
    public void finishTask() {

    }

    //销毁时解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


}
