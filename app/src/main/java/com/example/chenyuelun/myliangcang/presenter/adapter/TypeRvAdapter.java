package com.example.chenyuelun.myliangcang.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.model.entity.StoreTypeBean;
import com.example.chenyuelun.myliangcang.presenter.listener.OnItemClickListener;
import com.example.chenyuelun.myliangcang.utils.imageUtil.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/7/24.
 */

public class TypeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<StoreTypeBean.DataBean.ItemsBean> data = new ArrayList<>();
    private OnItemClickListener listener;

    public TypeRvAdapter(Context context) {
        this.context = context;

    }

    public void refresh(StoreTypeBean typeBean) {
        data.clear();
        data.addAll(typeBean.getData().getItems());
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        StoreTypeBean.DataBean.ItemsBean itemsBean = data.get(position);
        String new_cover_img = itemsBean.getNew_cover_img();
        ImageLoaderUtil.getInstance().showImage(viewHolder.ivItemType,new_cover_img,null);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_type)
        ImageView ivItemType;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public StoreTypeBean.DataBean.ItemsBean getItem(int position){
        return data.get(position);
    }


}
