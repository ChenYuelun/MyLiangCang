package com.example.chenyuelun.myliangcang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public abstract class BaseFragment extends Fragment {
    private FragmentActivity activity;
    private Unbinder bind;

    //标志位 表示fragment初始化完成
    public boolean isPrepared;

    //标志位  表示fragment已经显示
    public boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在view创建时由子类返回布局
        int id = getLayoutId();
        View view = null;
        if(id == 0) {
            TextView textView = new TextView(getContext());
            textView.setText("布局未定义，临时布局");
            view = textView;
        }else {
            view = inflater.inflate(id,container,false);
            activity = getSupportActivity();
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //在view创建完成时 初始化控件 并设置保存
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
        //初始化标题栏
        initTitle();
    }
    /**
     * 初始化views
     * */
    protected abstract void finishCreateView(Bundle savedInstanceState);


    private FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    //    子类返回布局Id 统一由父类加载布局并初始化控件
    protected abstract int getLayoutId();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }


    //销毁view时解绑控件
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    /**
     * 获取全局上下文
     * @return
     */
    public Context getApplicationContext(){
        return this.activity == null?(getActivity() == null?null:getActivity().getApplicationContext()):this.activity.getApplicationContext();
    }

    /**
     * fragment数据懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInVisible();
        }
    }



    /**
     * 当fragment显示的时候开始加载数据
     */
    protected void onVisible() {
        Log.e("TAG", "fragment获得焦点");

        //加载数据
        lazyLoad();

    }

    protected void initTitle() {
        Log.e("TAG", "basefragment == inittitle");
    }

    //加载数据的方法
    protected void lazyLoad() {

    }

    /**
     * 当fragment不显示的时候
     */
    protected void onInVisible() {
        Log.e("TAG", "fragment失去焦点");
    }

    /**
     * 主动调用  从网络获取数据
     */
    protected void loadData(){

    }

    /**
     * 主动调用  显示正在加载进度条
     */
    protected void showProgressBar(){

    }

    /**
     * 隐藏进度条
     */
    protected void hideProgressBar() {

    }

    /**
     * 初始化recyclerView
     */
    protected void initRecyclerView() {

    }

    /**
     * 初始化refreshLayout
     */
    protected void initRefreshLayout() {
    }

    /**
     * 设置数据显示
     */
    protected void finishTask() {

    }

}
