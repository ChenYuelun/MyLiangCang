package com.example.chenyuelun.myliangcang.commen;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class MyApplication extends Application {
    private static Context context;

    private static Handler handler;

    public static Handler getHandler() {
        return handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        handler = new Handler();
    }

    public static Context getInstance() {
        return context;
    }
}
