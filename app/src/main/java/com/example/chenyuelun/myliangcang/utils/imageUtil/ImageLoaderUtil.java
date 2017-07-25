package com.example.chenyuelun.myliangcang.utils.imageUtil;

import android.view.View;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class ImageLoaderUtil implements ImageLoader {
    //封装构造器，懒汉式单例
    private static ImageLoaderUtil manager = new ImageLoaderUtil();
    private ImageLoader imageLoader;
    private ImageLoaderUtil(){
        //默认使用glide
        imageLoader = new GlideImageLoader();
    }

    //提供随时更换加载框架的方法
    public void setImageLoader(ImageLoader imageLoader) {
        if(imageLoader != null) {
            this.imageLoader = imageLoader;
        }
    }

    public static ImageLoaderUtil getInstance(){
        return  manager;
    }


    @Override
    public void showImage(View v, String url, ImageLoaderOptions options) {
        imageLoader.showImage(v,url,options);
    }

    @Override
    public void showImage(View v, int drawable, ImageLoaderOptions options) {
        imageLoader.showImage(v,drawable,options);
    }
}
