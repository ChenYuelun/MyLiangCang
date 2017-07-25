package com.example.chenyuelun.myliangcang.utils.imageUtil;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;

/**
 * Created by chenyuelun on 2017/7/25.
 * 封装glide 依照此例封装picasso
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void showImage(View v, String url, ImageLoaderOptions options) {
        if(v instanceof ImageView) {
            ImageView imageView = (ImageView) v;
            DrawableTypeRequest<String> dtr = Glide.with(imageView.getContext()).load(url);
            loadOptions(dtr,options).into(imageView);
        }
    }

    @Override
    public void showImage(View v, int drawable, ImageLoaderOptions options) {

    }

    //这个方法用来装载有外部设置的参数
    private GenericRequestBuilder loadOptions(DrawableTypeRequest<String> dtr, ImageLoaderOptions options) {
        if(options == null) {
            return  dtr;
        }
        if(options.getPlaceHolder() != -1) {
            dtr.placeholder(options.getPlaceHolder());
        }

        if(options.getErrorDrawable() != -1) {
            dtr.error(options.getErrorDrawable());
        }


        return dtr;
    }

}
