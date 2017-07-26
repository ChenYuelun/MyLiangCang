package com.example.chenyuelun.myliangcang.utils.imageUtil;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class ImageLoaderOptions {

    private int placeHolder = -1;//加载过程中显示的图片
    private int errorDrawable = -1;//加载错误时显示的图片

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(int errorDrawable) {
        this.errorDrawable = errorDrawable;
    }

    public ImageLoaderOptions(int placeHolder, int errorDrawable){
        this.placeHolder = placeHolder;
        this.errorDrawable = errorDrawable;
    }

    public ImageLoaderOptions(){}


}
