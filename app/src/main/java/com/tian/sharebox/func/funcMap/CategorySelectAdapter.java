package com.tian.sharebox.func.funcMap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tian.sharebox.R;
import com.tian.sharebox.data.CategoryData;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/19
 * @describe
 */

public class CategorySelectAdapter extends RecyclerView.Adapter<CategorySelectAdapter.CategorySelectHolder>
{
    private CategoryCallback listener = null;
    private Context mContext = null;

    public CategorySelectAdapter(Context context)
    {
        mContext = context;
    }

    private ArrayList<CategoryData> categorys = new ArrayList<>(8);

    @Override
    public CategorySelectHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        CategorySelectHolder holder = new CategorySelectHolder(LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false));
        holder.setOnClick(listener);
        return holder;
    }

    public void setListener(CategoryCallback listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(CategorySelectHolder holder, int position)
    {
        if (categorys != null && !categorys.isEmpty())
        {
            holder.setData(categorys.get(position));
        }
    }

    public void setCategorys(ArrayList<CategoryData> categorys)
    {
        this.categorys = categorys;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return categorys.size();
    }

    static class CategorySelectHolder extends RecyclerView.ViewHolder
    {
        private TextView nameView;
        private CategoryData data;

        public CategorySelectHolder(View itemView)
        {
            super(itemView);
            nameView = (TextView) itemView.findViewById(R.id.my_category_item_name);
        }

        public void setData(CategoryData data)
        {
            this.data = data;
            nameView.setText(data.getCategoryName());
        }

        public void setOnClick(final CategoryCallback listener)
        {
            nameView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onClick(data);
                }
            });
        }

    }
}
