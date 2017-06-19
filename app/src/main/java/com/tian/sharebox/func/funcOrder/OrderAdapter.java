package com.tian.sharebox.func.funcOrder;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tian.sharebox.R;
import com.tian.sharebox.data.OrderData;

import java.util.ArrayList;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder>
{
    private ArrayList<OrderData> orderLists = null;
    private Context mContext;
    private OrderItemCallback callback;

    public OrderAdapter(Context context)
    {
        this.mContext = context;
    }

    public void setCallback(OrderItemCallback callback)
    {
        this.callback = callback;
    }

    public void setOrderLists(final ArrayList<OrderData> lists)
    {
        if (orderLists == null)
        {
            orderLists = lists;
            notifyItemRangeInserted(0, lists.size());
        } else
        {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback()
            {
                @Override
                public int getOldListSize()
                {
                    return orderLists.size();
                }

                @Override
                public int getNewListSize()
                {
                    return lists.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
                {
                    return orderLists.get(oldItemPosition).getOrderId() ==
                            lists.get(newItemPosition).getOrderId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
                {
                    return false;
                }
            });
            orderLists = lists;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        OrderHolder orderHolder = new OrderHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_order, parent, false));
        orderHolder.setListener(callback);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position)
    {
        if (orderLists != null)
        {
            OrderData data = orderLists.get(position);
            holder.setOrderData(data);
        }
    }

    @Override
    public int getItemCount()
    {
        return orderLists == null ? 0 : orderLists.size();
    }

    static class OrderHolder extends RecyclerView.ViewHolder
    {
        private OrderData orderData = null;
        private TextView orderDateText;
        private TextView orderIdText;
        private TextView orderStateText;
        private TextView goodsText;
        private TextView startTimeText;
        private TextView endTimeText;
        private View endTimeLayout = null;

        public OrderHolder(View itemView)
        {
            super(itemView);
            orderDateText = (TextView) itemView.findViewById(R.id.my_order_item_date);
            orderIdText = (TextView) itemView.findViewById(R.id.my_order_item_body_order_id);
            orderStateText = (TextView) itemView.findViewById(R.id.my_order_item_body_order_state);
            goodsText = (TextView) itemView.findViewById(R.id.my_order_item_body_order_goods);
            startTimeText = (TextView) itemView.findViewById(R.id.my_order_item_body_start_time);
            endTimeText = (TextView) itemView.findViewById(R.id.my_order_item_body_end_time);
            endTimeLayout = itemView.findViewById(R.id.my_order_item_body_end_time_layout);
        }

        public void setOrderData(OrderData data)
        {
            this.orderData = data;
            orderDateText.setText("");
            orderIdText.setText(super.itemView.getResources().getString(R.string.activity_order_order_id, data.getOrderId()));
            orderStateText.setText(super.itemView.getResources().getString(R.string.activity_order_order_state, data.getOrderState()));
            goodsText.setText(data.getGoodsName());
            startTimeText.setText(super.itemView.getResources().getString(R.string.activity_order_order_start_time, data.getStartTime()));
            if (data.getEndTime()==null)
            {
                endTimeLayout.setVisibility(View.GONE);
            }else
            {
                endTimeLayout.setVisibility(View.VISIBLE);
                endTimeText.setText(super.itemView.getResources().getString(R.string.activity_order_order_end_time, data.getEndTime())); 
            }
            
        }

        public void setListener(final OrderItemCallback orderItemCallback)
        {
            super.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    orderItemCallback.onClick(orderData);
                }
            });
        }
    }
}
