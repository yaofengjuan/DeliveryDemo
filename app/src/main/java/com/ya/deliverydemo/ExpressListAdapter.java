package com.ya.deliverydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ya.deliverydemo.ExpressList.ShowapiResBodyEntity.ExpressListEntity;

import java.util.List;

class ExpressListAdapter extends RecyclerView.Adapter<ExpressListAdapter.CustomViewHolder> {


    private Context mContext;
    private boolean isFlip;
    private List<ExpressListEntity> data;

    public ExpressListAdapter(Context mContext, boolean isFlip, List<ExpressListEntity> data) {
        this.mContext = mContext;
        this.isFlip = isFlip;
        this.data = data;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (!isFlip) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.round_image, null);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_express, null);
        }
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ExpressListEntity info = getItem(position);
        if (isFlip) {
            holder.companyName.setText(info.expName == null ? "" : info.expName);
            holder.call.setText(info.phone == null ? "" : info.phone);
            holder.url.setText(info.url == null ? "" : info.url);
        } else {
            String imgUrl = info.imgUrl;
            Log.d(mContext.getClass().getName(), "imgUrl:" + imgUrl);
            Glide.with(mContext).load(info.imgUrl).into(holder.expImg);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, QueryActivity.class).putExtra("simpleName", info.simpleName).putExtra("expName", info.expName).putExtra("logoUrl", info.imgUrl));
            }
        });

    }


    public ExpressListEntity getItem(int position) {
        return data.get(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView expImg;
        public TextView companyName;
        public TextView call;
        public TextView url;

        public CustomViewHolder(View itemView) {
            super(itemView);
            expImg = (ImageView) itemView.findViewById(R.id.demo);
            companyName = (TextView) itemView.findViewById(R.id.express_name);
            call = (TextView) itemView.findViewById(R.id.express_phone);
            url = (TextView) itemView.findViewById(R.id.express_url);
        }
    }
}