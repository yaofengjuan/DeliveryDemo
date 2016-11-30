package com.ya.deliverydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ya.deliverydemo.CollectionPresenter;
import com.ya.deliverydemo.ui.CollectionActivity;
import com.ya.deliverydemo.ui.QueryActivity;
import com.ya.deliverydemo.R;
import com.ya.deliverydemo.entity.ExpressInfo;

import java.util.List;

/**
 * Created by YaoFengjuan on 2016/6/24.
 */
public class ExpressInfoAdapter extends RecyclerView.Adapter<ExpressInfoAdapter.ViewHolder> {

    private Context context;
    private List<ExpressInfo> list;

    private boolean isShowDelete = false;

    public ExpressInfoAdapter(Context context, List<ExpressInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    @Override
    public ExpressInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpressInfoAdapter.ViewHolder holder, final int position) {
        final ExpressInfo data = getItem(position);
        Glide.with(context).load(data.getLogoUrl()).into(holder.logo);
        holder.remarkName.setText(data.getExpSpellName());
        holder.expLastContent.setText(data.getMailNo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, QueryActivity.class).putExtra("simpleName", data.getSimpleName()).putExtra("expName", data.getExpSpellName()).putExtra("logoUrl", data.getLogoUrl()).putExtra("mailNo", data.getMailNo()));
            }
        });
        if (isShowDelete) {
            holder.delete.setVisibility(View.VISIBLE);
        } else {
            holder.delete.setVisibility(View.GONE);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CollectionPresenter(context, (CollectionActivity) context).deleteMyOrder(data.getMailNo());
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private ExpressInfo getItem(int position) {
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView logo;
        private final TextView remarkName;
        private final TextView expLastContent;
        private final TextView delete;

        public ViewHolder(View itemView) {
            super(itemView);
            logo = (RoundedImageView) itemView.findViewById(R.id.logo);
            remarkName = (TextView) itemView.findViewById(R.id.remark_name);
            expLastContent = (TextView) itemView.findViewById(R.id.current_exp_content);
            delete = (TextView) itemView.findViewById(R.id.delete);

        }
    }
}
