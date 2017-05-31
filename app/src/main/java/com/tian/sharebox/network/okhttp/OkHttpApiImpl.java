package com.tian.sharebox.network.okhttp;

import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/19
 * @describe
 */

public class OkHttpApiImpl
{
    private static OkHttpApiImpl instance = null;
    private OkHttpClient okHttpClient = null;

    public static OkHttpApiImpl getInstance()
    {
        if (instance == null)
        {
            synchronized (OkHttpApiImpl.class)
            {
                instance = new OkHttpApiImpl();
            }
        }
        return instance;
    }

    private OkHttpApiImpl()
    {
        okHttpClient = new OkHttpClient();
    }

    /**
     * 同步方法
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String getString(String url) throws IOException
    {
        Response response = getResponse(url);
        return response.body().toString();
    }

    /**
     * 异步方法
     *
     * @param url
     * @param callback
     * @throws IOException
     */
    public void getString(String url, ShareBoxCallback callback) throws IOException
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 同步方法
     *
     * @param url
     * @return
     * @throws IOException
     */
    public Response getResponse(String url) throws IOException
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 异步方法
     *
     * @param url
     * @param callback
     * @throws IOException
     */
    public void getResponse(String url, ShareBoxCallback callback) throws IOException
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    private Request buildRequest(String url, HashMap<String, String> params)
    {
        RequestBody formBody = new FormBody.Builder()
                .add("", "")
                .build(); 
        return new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
    }

    /**
     * post 同步方法
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String postString(String url, HashMap<String, String> params) throws IOException
    {
        return "";
    }

    /**
     * 同步post方法
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public Response postResponse(String url, HashMap<String, String> params) throws IOException
    {
        return null;
    }

    /**
     * 异步post方法
     *
     * @param url
     * @param callback
     * @param params
     * @throws IOException
     */
    public void postString(String url, ShareBoxCallback callback, HashMap<String, String> params) throws IOException
    {

    }

    /**
     * 异步post方法
     *
     * @param url
     * @param callback
     * @param params
     * @throws IOException
     */
    public void postResponse(String url, ShareBoxCallback callback, HashMap<String, String> params) throws IOException
    {

    }


}
