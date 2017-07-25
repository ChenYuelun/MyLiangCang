package com.example.chenyuelun.myliangcang.model.store;

import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public interface OnLodaDataListener {
    void onSuccess(StoreTypeBean storeTypeBean);
    void onError(Throwable e);
}
