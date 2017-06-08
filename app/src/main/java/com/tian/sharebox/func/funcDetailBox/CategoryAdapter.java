package com.tian.sharebox.func.funcDetailBox;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tian.sharebox.R;
import com.tian.sharebox.data.CategoryData;
import com.tian.sharebox.data.MessageData;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/26
 * @describe
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MessageViewHolder>
{
    private Context mContext;
    ArrayList<? extends CategoryData> categoryDataList = null;

    public CategoryAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    public void setCategoryDataList(final ArrayList<? extends CategoryData> messageList)
    {
        if (categoryDataList == null)
        {
            categoryDataList = messageList;
            notifyItemRangeInserted(0, messageList.size());
        } else
        {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback()
            {
                @Override
                public int getOldListSize()
                {
                    return categoryDataList.size();
                }

                @Override
                public int getNewListSize()
                {
                    return messageList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
                {
                    return categoryDataList.get(oldItemPosition).getTitle() ==
                            messageList.get(newItemPosition).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
                {
                    CategoryData product = messageList.get(newItemPosition);
                    CategoryData old = messageList.get(oldItemPosition);
                    return Objects.equals(product.getTitle(), old.getTitle());
                }
            });
            categoryDataList = messageList;
            result.dispatchUpdatesTo(this);
        }
    }

    public void deleteItemPosition(int position)
    {
        if (categoryDataList != null)
        {
            Log.i("aa", "size==" + categoryDataList.size() + ",position == " + position);
            categoryDataList.remove(position);
            notifyItemRemoved(position);
            if (position != categoryDataList.size())
            {
                notifyItemRangeChanged(position, categoryDataList.size() - position);
            }
        }
    }

    public void deleteAllItem()
    {
        if (categoryDataList != null)
        {
            categoryDataList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MessageViewHolder viewHolder = new MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_category, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position)
    {
        if (categoryDataList != null)
        {
            CategoryData data = categoryDataList.get(position);
            holder.categoryTitle.setText(data.getTitle());
            holder.categoryAvailableTotal.setText(data.getAvailableTotal());
            holder.setData(data);
            holder.setPosition(position);
            Log.i("aa", "onBindViewHolder:" + position + ",title=" + data.getTitle());
        }
    }

    @Override
    public int getItemCount()
    {
        return categoryDataList == null ? 0 : categoryDataList.size();
    }


    static class MessageViewHolder extends RecyclerView.ViewHolder
    {
        public CategoryData data;
        public int position = -1;
        public TextView categoryTitle;//物品名称
        public TextView categoryAvailableTotal;//可用数目

        public MessageViewHolder(View itemView)
        {
            super(itemView);
            categoryTitle = (TextView) itemView.findViewById(R.id.my_category_item_title_text);
            categoryAvailableTotal = (TextView) itemView.findViewById(R.id.my_category_item_number_text);
        }

        public void setData(CategoryData data)
        {
            this.data = data;
        }

        public void setPosition(int position)
        {
            this.position = position;
        }
    }
}
