package com.tian.sharebox.func.funcMessage;

import com.tian.sharebox.data.MessageData;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/26
 * @describe
 */

public interface MessageItemCallback
{
    void onClick(MessageData data);
    void onLongClick(MessageData data, int position); 
}
