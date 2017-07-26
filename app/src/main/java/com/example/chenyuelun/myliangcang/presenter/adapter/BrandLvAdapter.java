package com.example.chenyuelun.myliangcang.presenter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.model.entity.StoreBrandBean;
import com.example.chenyuelun.myliangcang.utils.imageUtil.ImageLoaderOptions;
import com.example.chenyuelun.myliangcang.utils.imageUtil.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/7/25.
 */

public class BrandLvAdapter extends BaseAdapter {
    private final Context context;
    private List<StoreBrandBean.DataBean.ItemsBean> datas = new ArrayList<>();

    ImageLoaderOptions options = new ImageLoaderOptions(R.drawable.brand_logo_empty, R.drawable.brand_logo_empty);

    public BrandLvAdapter(Context context) {
        this.context = context;
    }

    public void refresh(List<StoreBrandBean.DataBean.ItemsBean> datas, boolean isLoadMore) {
        if(isLoadMore) {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }else {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_lv_brand, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StoreBrandBean.DataBean.ItemsBean itemsBean = datas.get(position);

        ImageLoaderUtil.getInstance().showImage(viewHolder.ivBrandIcon, itemsBean.getBrand_logo(), options);
        viewHolder.tvBrandname.setText(itemsBean.getBrand_name());


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_brand_icon)
        ImageView ivBrandIcon;
        @BindView(R.id.tv_brandname)
        TextView tvBrandname;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
