package com.example.chenyuelun.myliangcang.commen.network;

import com.example.chenyuelun.myliangcang.commen.MyApplication;
import com.example.chenyuelun.myliangcang.utils.CommonUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient;
    static {
        initOkHttpClient();
    }

    public static <T>T createApi(Class<T> clazz,String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 初始化okhttp,设置缓存，设置超时时间,设置打印日志，设置UI拦截器
     */
    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //懒汉式单例
        if(mOkHttpClient == null) {
            synchronized (RetrofitHelper.class){
                if(mOkHttpClient == null) {
                    Cache cache = new Cache(new File(MyApplication.getInstance().getCacheDir(),"HttpCache"),1024*1024*10);
                    mOkHttpClient = new OkHttpClient
                            .Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)//日志
                            .addNetworkInterceptor(new CacheInterceptor())//缓存
                            .addNetworkInterceptor(new StethoInterceptor())//调试
                            .retryOnConnectionFailure(true)//是否自动重连
                            .connectTimeout(30, TimeUnit.SECONDS)//连接超时
                            .writeTimeout(20, TimeUnit.SECONDS)//写入超时
                            .readTimeout(20, TimeUnit.SECONDS)//读取超时
                            .build();
                }
            }
        }
    }


    /**
     * 为okhttp添加缓存，这里是考虑到服务器不支持缓存时，从而让okhttp支持缓存
     */
    private static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            // 有网络时 设置缓存超时时间1个小时
            int maxAge = 60 * 3;
            // 无网络时，设置超时为1天
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();
            if (CommonUtil.isNetworkAvailable(MyApplication.getInstance())) {
                //有网络时只从网络获取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                //无网络时只从缓存中读取
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetworkAvailable(MyApplication.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }
}
