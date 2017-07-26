package com.example.chenyuelun.myliangcang.commen.network.api;

import com.example.chenyuelun.myliangcang.model.entity.StoreBrandBean;
import com.example.chenyuelun.myliangcang.model.entity.StoreHomeBean;
import com.example.chenyuelun.myliangcang.model.entity.StoreTopicBean;
import com.example.chenyuelun.myliangcang.model.entity.StoreTypeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public interface ApiService {
    //商店 分类
    @GET("goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0")
    Observable<StoreTypeBean> getStoreType();

    //商店 品牌
    @GET("brandList")
    Observable<StoreBrandBean> getStoreBrand(@Query("app_key") String android, @Query("count") int count, @Query("page") int page, @Query("sig") String sig, @Query("v") String v);

    //商店 首页
    @GET("newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0")
    Observable<StoreHomeBean> getStoreHome();

    //商店 品牌
    @GET("shopSpecial")
    Observable<StoreTopicBean> getStoreTopic(@Query("app_key") String android, @Query("count") int count, @Query("page") int page, @Query("sig") String sig, @Query("uid") String uid,@Query("user_token") String user_token,@Query("v") String v);


}
