package com.ya.deliverydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ya.deliverydemo.R;
import com.ya.deliverydemo.Utils;
import com.ya.deliverydemo.entity.ExpressContent;
import com.ya.deliverydemo.entity.ExpressContent.ShowapiResBodyEntity.DataEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by YaoFengjuan on 2016/6/20.
 */
public class ExpressContentAdapter extends RecyclerView.Adapter<ExpressContentAdapter.ViewHolder> {

    private Context context;
    private HashMap<String, List<DataEntity>> dataMap = new HashMap<>();

    public ExpressContentAdapter(Context context) {
        this.context = context;
    }


    public void refresh(ExpressContent content) {
        if (content != null) {
            recombine(content);
        } else {
            dataList.clear();
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_express_tracking, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entity entity = dataList.get(position);

        holder.date.setText(entity.getKeyDate());
        holder.content.removeAllViews();
        for (DataEntity content : entity.getContentValues()) {

            // holder.content. // TODO: 2016/6/20
        }
        for (int i = 0; i < entity.getContentValues().size(); i++) {
            DataEntity content = entity.getContentValues().get(i);
            holder.content.addView(getView(position, i, content.time, content.context));
        }
    }

    public View getView(int position, int index, String timeStr, String content) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_express_tracking_content, null);
        TextView time = (TextView) contentView.findViewById(R.id.time);
        TextView expressContent = (TextView) contentView.findViewById(R.id.express_content);
        if (position == 0 && index == 0) {
            expressContent.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
        time.setText(Utils.getTime(timeStr));
        expressContent.setText(Html.fromHtml(content));
        return contentView;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final LinearLayout content;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            content = (LinearLayout) itemView.findViewById(R.id.content);
        }
    }

    private void recombine(ExpressContent content) {
        List<DataEntity> data = content.showapi_res_body.data;
        if (data.size() > 0) {
            for (DataEntity entity : data) {
                String time = Utils.getDate(entity.time);
                if (!TextUtils.isEmpty(time)) {
                    if (dataMap.containsKey(time)) {
                        List<DataEntity> temporary = dataMap.get(time);
                        temporary.add(entity);
                        dataMap.put(time, temporary);
                    } else {
                        List<DataEntity> temporary = new ArrayList<>();
                        temporary.add(entity);
                        dataMap.put(time, temporary);
                    }
                }
            }
            dataList.clear();
            for (Entry<String, List<DataEntity>> entry : dataMap.entrySet()) {
                Entity entity = new Entity();
                entity.setKeyDate(entry.getKey());
                entity.setContentValues(entry.getValue());
                dataList.add(entity);
            }
        }
        Collections.sort(dataList, new CalendarComparator());
    }

    // 自定义比较器：按日期来排序
    static class CalendarComparator implements Comparator {
        public int compare(Object object1, Object object2) {// 实现接口中的方法
            Entity p1 = (Entity) object1; // 强制转换
            Entity p2 = (Entity) object2;
            return p2.keyDate.compareTo(p1.keyDate);
        }
    }

    private List<Entity> dataList = new ArrayList<>();

    class Entity {
        String keyDate;
        List<DataEntity> contentValues;

        public String getKeyDate() {
            return keyDate;
        }

        public void setKeyDate(String keyDate) {
            this.keyDate = keyDate;
        }

        public List<DataEntity> getContentValues() {
            return contentValues;
        }

        public void setContentValues(List<DataEntity> contentValues) {
            this.contentValues = contentValues;
        }
    }
}
