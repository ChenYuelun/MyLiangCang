package com.example.chenyuelun.myliangcang.commen.network.api;

import com.example.chenyuelun.myliangcang.model.bean.StoreTypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public interface ApiService {
    @GET("goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0")
    Observable<StoreTypeBean> getStoreType();
}
