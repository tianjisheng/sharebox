package com.tian.sharebox.network.okhttp.callback;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/31
 * @describe
 */

public abstract class ShareBoxCallback implements Callback
{
    public static enum CallBackType
    {
        Response,
        String
    }

    private CallBackType type;

    public ShareBoxCallback(CallBackType type)
    {
        this.type = type;
    }

    public ShareBoxCallback()
    {
        this.type = CallBackType.String;
    }

    @Override
    public void onFailure(Call call, IOException e)
    {
        e.printStackTrace();
        if (e.getMessage().contains("Cancel"))
        {
            handleCancelCall(call,e);
        }else
        {
            handleCallbackFailure(call, e);
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {
        if (type == CallBackType.Response)
        {
            handleCallbackSuccess(call, response);
        } else
        {
            while (true)
            {
                if (response == null)
                {
                    handleResultFailure(call);
                    break;
                }

//                LogUtil.i(""+response);
                String res = response.body().string();
                if (res == null)
                {
                    handleResultFailure(call);
                    break;
                }
                LogUtil.i("call", call.request().toString(), "response", res);
                JSONObject json = null;
                try
                {
                    json = JSONObject.parseObject(res);
                }catch (JSONException e)
                {
                    json = null;
                    e.printStackTrace();
                }
                        
                if (json == null)
                {
                    handleResultFailure(call);
                    break;
                }
                int state = json.getIntValue("state");
                if (state == 1)
                {
                    handleResultSuccess(call, json);
                } else
                {
                    handleResultFailure(call);
                }
                break;

            }
        }
    }

    public abstract void handleCallbackFailure(Call call, IOException e);

    protected void handleResultFailure(Call call)
    {
        handleCallbackFailure(call,null);
    }

    protected void handleResultSuccess(Call call, JSONObject Json)
    {

    }

    protected void handleCallbackSuccess(Call call, Response response)
    {

    }
    
    protected void handleCancelCall(Call call,IOException e)
    {
        
    }

}
