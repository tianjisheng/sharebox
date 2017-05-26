package com.tian.sharebox.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewStub;

import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.tian.sharebox.R;
import com.tian.sharebox.data.MessageData;
import com.tian.sharebox.ui.MessageAdapter;
import com.tian.sharebox.ui.MessageItemCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/24
 * @describe
 */

public class UserMessageActivity extends UserBaseActivity implements SwipyRefreshLayout.OnRefreshListener
{
    private ViewStub mViewStub;
    private MessageAdapter adapter;
    private SwipyRefreshLayout layout;
    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_my_messages;
    }

    @Override
    protected int getToolbarId()
    {
        return R.id.activity_user_message_toolbar;
    }
    @Override
    protected int getTitleId()
    {
        return R.id.activity_user_message_toolbar_title_text;
    }

    @Override
    protected void setContent()
    {
        
    }

    @Override
    protected void initSelfLayout()
    {
        mViewStub = (ViewStub) findViewById(R.id.my_message_delete_stub);
        
        layout = (SwipyRefreshLayout) findViewById(R.id.my_messages_swipy_refresh_layout); 
        layout.setOnRefreshListener(this);
        
        
        adapter = new MessageAdapter(getApplicationContext());
        adapter.setCallback(new MessageItemCallback()
        {
            @Override
            public void onClick(MessageData data)
            {
                Log.i("aa","onClick:"+data.getMessageTitle());
            }

            @Override
            public void onLongClick(MessageData data, int position)
            {
                Log.i("aa","onLongClick");
                mViewStub.inflate();
            }
        });
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_messages_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(adapter);
        
    }
    
    private void initData()
    {
        ArrayList<MessageData> list = new ArrayList<>();
        MessageData data = new MessageData();
        data.setId(1);
        data.setMessageDate("2017-5-26 00:00");
        data.setMessageTitle("过端午，有惊喜");
        list.add(data);

        MessageData data1 = new MessageData();
        data1.setId(2);
        data1.setMessageDate("2017-5-24 00:00");
        data1.setMessageTitle("安全出行，共创文明");
        list.add(data1);
        
        MessageData data2 = new MessageData();
        data2.setId(3);
        data2.setMessageDate("2017-5-23 00:00");
        data2.setMessageTitle("一起和阳光玩游戏");
        list.add(data2);

        MessageData data3 = new MessageData();
        data3.setId(4);
        data3.setMessageDate("2017-5-26 00:00");
        data3.setMessageTitle("测试");
        data3.setMessageBody("是带有body的消息");
        list.add(data3);
        
        adapter.setMessageDataList(list);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction)
    {
         Log.i("aa",""+direction);
    }
}
