package com.tian.sharebox.funcMessage;

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
import com.tian.sharebox.data.MessageData;

import java.util.ArrayList; 
import java.util.Objects;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/26
 * @describe
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>
{
    private MessageItemCallback callback = null;
    private Context mContext;
    ArrayList<? extends MessageData> messageDataList = null;

    public MessageAdapter(Context mContext)
    {
        this.mContext = mContext;
    }
    
    public void setCallback(MessageItemCallback callback)
    {
        this.callback = callback;
    }
    
    public void setMessageDataList(final ArrayList<? extends MessageData> messageList)
    {
        if (messageDataList == null)
        {
            messageDataList = messageList;
            notifyItemRangeInserted(0,messageList.size());
        }else
        {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return messageDataList.size();
                }

                @Override
                public int getNewListSize() {
                    return messageList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return messageDataList.get(oldItemPosition).getId() ==
                            messageList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MessageData product = messageList.get(newItemPosition);
                    MessageData old = messageList.get(oldItemPosition);
                    return product.getId() == old.getId()
                            && Objects.equals(product.getMessageTitle(), old.getMessageTitle())
                            && Objects.equals(product.getMessageDate(), old.getMessageDate());
                }
            });
            messageDataList = messageList;
            result.dispatchUpdatesTo(this);
        }
    }
    
    public void deleteItemPosition(int position)
    {
        if (messageDataList!=null)
        {
            Log.i("aa","size=="+messageDataList.size()+",position == "+position);
            messageDataList.remove(position);
            notifyItemRemoved(position);
            if (position!=messageDataList.size())
            {
                notifyItemRangeChanged(position,messageDataList.size()-position);
            }
        }
    }
    
    public void deleteAllItem()
    {
        if (messageDataList!=null)
        {
            messageDataList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MessageViewHolder viewHolder = new MessageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_message, parent,false));
        viewHolder.setListener(callback);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position)
    {
         if (messageDataList!=null)
         {
             MessageData data = messageDataList.get(position);
             holder.messageTitle.setText(data.getMessageTitle());
             holder.messageDate.setText(data.getMessageDate());
             if (!"".equals(data.getMessageBody()))
             {
                 holder.messageBody.setVisibility(View.VISIBLE);
                 holder.messageBody.setText(data.getMessageBody());
             }
             holder.messageTitleIcon.setBackgroundColor(Color.GREEN);
             holder.setData(data);
             holder.setPosition(position);
             Log.i("aa","onBindViewHolder:"+position+",title="+data.getMessageTitle());
         }
    }

    @Override
    public int getItemCount()
    {
        return messageDataList == null ? 0 : messageDataList.size();
    }
    
    

    static class MessageViewHolder extends RecyclerView.ViewHolder
    {
        public MessageData data;
        public int position = -1;
        public TextView messageTitle;
        public TextView messageBody;
        public TextView messageDate;
        public ImageView messageTitleIcon;

        public MessageViewHolder(View itemView)
        {
            super(itemView);
            messageTitle = (TextView) itemView.findViewById(R.id.my_message_item_title_text);
            messageTitleIcon = (ImageView) itemView.findViewById(R.id.my_message_title_img);
            messageDate = (TextView) itemView.findViewById(R.id.my_message_item_date);
            messageBody = (TextView) itemView.findViewById(R.id.my_message_item_body_text);
        }
        
        public void setData(MessageData data)
        {
            this.data = data;
        }
        
        public void setPosition(int position)
        {
            this.position = position;
        }
        
        public void setListener(final MessageItemCallback listener)
        {
            super.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onClick(data);
                }
            });
            
            super.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    listener.onLongClick(data,position);
                    return false;
                }
            });
        }

    }
}
