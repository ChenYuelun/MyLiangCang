package com.example.chenyuelun.myliangcang.utils.imageUtil;

import android.view.View;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public interface ImageLoader {
    void showImage(View v,String url,ImageLoaderOptions options);
    void showImage(View v, int drawable,ImageLoaderOptions options);

}
