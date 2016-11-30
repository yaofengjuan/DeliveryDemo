package com.ya.deliverydemo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ya.deliverydemo.ui.QueryActivity;
import com.ya.deliverydemo.R;
import com.ya.deliverydemo.entity.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import java.util.HashMap;
import java.util.List;

public class ExpressListAdapter extends RecyclerView.Adapter<ExpressListAdapter.CustomViewHolder> {


    private Context mContext;
    //private boolean isFlip;
    private List<ExpressListEntity> data;

    //private HashMap<Integer, Boolean> isFlipMap = new HashMap<>();
    //private HashMap<Integer, Integer> isNotifyItem = new HashMap<>();
    private Interpolator accelerator = new AccelerateInterpolator();
    private Interpolator decelerator = new DecelerateInterpolator();

    public ExpressListAdapter(Context mContext, boolean isFlip, List<ExpressListEntity> data) {
        this.mContext = mContext;
        //this.isFlip = isFlip;
        this.data = data;
    }

    public void refresh(List<ExpressListEntity> data) {
        this.data.clear();
        this.data.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_express, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final ExpressListEntity info = getItem(position);
        holder.companyName.setText(info.expName == null ? "" : info.expName);
        holder.call.setText(info.phone == null ? "" : info.phone);
        holder.url.setText(info.url == null ? "" : info.url);
        Glide.with(mContext).load(info.imgUrl).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener()

                                           {
                                               @Override
                                               public void onClick(View v) {
                                                   mContext.startActivity(new Intent(mContext, QueryActivity.class).putExtra("simpleName", info.simpleName).putExtra("expName", info.expName).putExtra("logoUrl", info.imgUrl));
                                               }
                                           }

        );

    }


    public ExpressListEntity getItem(int position) {
        return data.get(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final ImageView image;
        public ImageView expImg;
        public TextView companyName;
        public TextView call;
        public TextView url;
        public TextView toDescri;

        public CustomViewHolder(View itemView) {
            super(itemView);
            expImg = (ImageView) itemView.findViewById(R.id.demo);
            image = (ImageView) itemView.findViewById(R.id.image);
            companyName = (TextView) itemView.findViewById(R.id.express_name);
            call = (TextView) itemView.findViewById(R.id.express_phone);
            url = (TextView) itemView.findViewById(R.id.express_url);
            toDescri = (TextView) itemView.findViewById(R.id.to_descri);
        }
    }
}