package com.example.chenyuelun.myliangcang.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chenyuelun.myliangcang.R;
import com.example.chenyuelun.myliangcang.model.entity.StoreHomeBean;
import com.example.chenyuelun.myliangcang.utils.imageUtil.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/7/26.
 */

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;

    private List<StoreHomeBean.DataBean.ItemsBean.ListBeanX> datas = new ArrayList<>();

    public HomeRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        StoreHomeBean.DataBean.ItemsBean.ListBeanX listBean = datas.get(position);
        int type = listBean.getHome_type();
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item_hometype1, parent, false));

            case 2:
                return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item_hometype2, parent, false));

            case 4:
                return new ViewHolder4(LayoutInflater.from(context).inflate(R.layout.item_hometype4, parent, false));

            case 6:
                return new ViewHolder6(LayoutInflater.from(context).inflate(R.layout.item_hometype6, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final StoreHomeBean.DataBean.ItemsBean.ListBeanX listBean = datas.get(position);
        if (holder instanceof ViewHolder1) {
            ViewHolder1 v1 = (ViewHolder1) holder;
            String pic_url = listBean.getOne().getPic_url();
            ImageLoaderUtil.getInstance().showImage(v1.ivHometype1, pic_url, null);
            v1.ivHometype1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClicked(listBean.getOne().getTopic_url(), listBean.getOne().getTopic_name(), listBean.getOne().getContent_id());
                    }
                }
            });

        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 v2 = (ViewHolder2) holder;
            ImageLoaderUtil.getInstance().showImage(v2.iv1Hometype2, listBean.getOne().getPic_url(), null);
            ImageLoaderUtil.getInstance().showImage(v2.iv2Hometype2, listBean.getTwo().getPic_url(), null);

            v2.iv1Hometype2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getOne().getTopic_url(), listBean.getOne().getTopic_name(), listBean.getOne().getContent_id());
                }
            });

            v2.iv2Hometype2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getTwo().getTopic_url(), listBean.getTwo().getTopic_name(), listBean.getTwo().getContent_id());
                }
            });


        } else if (holder instanceof ViewHolder4) {
            ViewHolder4 v4 = (ViewHolder4) holder;
            ImageLoaderUtil.getInstance().showImage(v4.ivLeftTop, listBean.getOne().getPic_url(), null);
            ImageLoaderUtil.getInstance().showImage(v4.ivLeftBot, listBean.getTwo().getPic_url(), null);
            ImageLoaderUtil.getInstance().showImage(v4.ivRightTop, listBean.getThree().getPic_url(), null);
            ImageLoaderUtil.getInstance().showImage(v4.ivRightBot, listBean.getFour().getPic_url(), null);

            v4.ivLeftTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getOne().getTopic_url(), listBean.getOne().getTopic_name(), listBean.getOne().getContent_id());
                }
            });

            v4.ivLeftBot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getTwo().getTopic_url(), listBean.getTwo().getTopic_name(), listBean.getTwo().getContent_id());
                }
            });

            v4.ivRightTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getThree().getTopic_url(), listBean.getThree().getTopic_name(), listBean.getThree().getContent_id());
                }
            });

            v4.ivRightBot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(listBean.getFour().getTopic_url(), listBean.getFour().getTopic_name(), listBean.getFour().getContent_id());
                }
            });


        } else if (holder instanceof ViewHolder6) {
            ViewHolder6 v6 = (ViewHolder6) holder;
            String pic_url = listBean.getPic_url();
            ImageLoaderUtil.getInstance().showImage(v6.ivHometype6, pic_url, null);
//            ivHometype6.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        MainActivity mainActivity = (MainActivity) context;
//                        StoreTypeDetailsFragment storeTypeDetailsFragment = new StoreTypeDetailsFragment(listBean.getHome_id()+"");
//                        mainActivity.replaceFragment(storeTypeDetailsFragment);
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void refresh(List<StoreHomeBean.DataBean.ItemsBean.ListBeanX> list) {
        this.datas = list;
        notifyDataSetChanged();
    }



    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hometype1)
        ImageView ivHometype1;
        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class ViewHolder2 extends  RecyclerView.ViewHolder {
        @BindView(R.id.iv1_hometype2)
        ImageView iv1Hometype2;
        @BindView(R.id.iv2_hometype2)
        ImageView iv2Hometype2;

        public ViewHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class ViewHolder4 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_left_top)
        ImageView ivLeftTop;
        @BindView(R.id.iv_left_bot)
        ImageView ivLeftBot;
        @BindView(R.id.iv_right_top)
        ImageView ivRightTop;
        @BindView(R.id.iv_right_bot)
        ImageView ivRightBot;

        public ViewHolder4(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ViewHolder6 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hometype1)
        ImageView ivHometype6;

        public ViewHolder6(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    private OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public interface OnItemClickListener {
        void onItemClicked(String topic_url, String Topic_name, String content_id);
    }
}
