package com.tian.sharebox.func.funcGoodsDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tian.sharebox.R;
import com.tian.sharebox.data.CategoryData;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public class DetailGoodsAdapter extends RecyclerView.Adapter<DetailGoodsAdapter.GoodsHolder>
{
    private Context mContext;
    private ArrayList<CategoryData> list = new ArrayList<>(1);

    public DetailGoodsAdapter(Context context)
    {
        this.mContext = context;
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        GoodsHolder holder = new GoodsHolder(mContext, LayoutInflater.from(null).inflate(R.layout.item_my_goods, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsHolder holder, int position)
    {
        if (list != null)
        {
            holder.setCategoryData(list.get(position));
        }
    }

    public void setGoods(ArrayList<CategoryData> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return list == null ? 0 : list.size();
    }

    static class GoodsHolder extends RecyclerView.ViewHolder
    {
        private ImageView icon;
        private TextView name;
        private TextView charging;
        private CategoryData categoryData;
        private Context context;

        public GoodsHolder(Context context, View itemView)
        {
            super(itemView);
            this.context = context;
            icon = (ImageView) itemView.findViewById(R.id.item_my_goods_icon);
            name = (TextView) itemView.findViewById(R.id.item_my_goods_name);
            charging = (TextView) itemView.findViewById(R.id.item_my_goods_charging);
        }

        public void setCategoryData(CategoryData data)
        {
            this.categoryData = data;
            Glide.with(context).load(categoryData.getIconPath()).into(icon);
            name.setText(categoryData.getCategoryName());
            charging.setText(categoryData.getCategoryCharging());
        }

    }
}
