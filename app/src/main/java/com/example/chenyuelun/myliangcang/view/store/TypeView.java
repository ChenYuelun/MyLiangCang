package com.example.chenyuelun.myliangcang.view.store;

import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public interface TypeView {


//    /**
//     * 主动调用  显示正在加载进度条
//     */
//    void showProgressBar();
//
//    /**
//     * 隐藏进度条
//     */
//    void hideProgressBar();
//
    /**
     * 设置数据显示
     * @param storeTypeBean
     */
     void finishTask(StoreTypeBean storeTypeBean);
}
