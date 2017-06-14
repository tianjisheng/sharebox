package com.tian.sharebox.network.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.tian.sharebox.network.okhttp.callback.ShareBoxCallback;
import com.tian.sharebox.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                long t1 = System.nanoTime();
                Request request = chain.request();
                LogUtil.i(String.format("request %s %s%n%s",request.url(),request.headers(),chain.connection()));
                Response response = chain.proceed(request);
                MediaType type = response.body().contentType();
                String content = response.body().string();
                long t2 = System.nanoTime();
                LogUtil.i(String.format("receive %s %.1fms%n%s%s",request.url(),(t2-t1)/1e6d,response.headers(),content));
                response.close();
                return response.newBuilder()
                        .body(ResponseBody.create(type,content))
                        .build();
            }
        });
        okHttpClient = builder.build();
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
                .tag(url)
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
                .tag(url)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    private Request buildRequest(String url, HashMap<String, String> params)
    {
        FormBody.Builder builder = new FormBody.Builder();
        JSONObject jsonObject = new JSONObject();
        if (params != null && params.size() > 0)
        {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            Iterator<Map.Entry<String, String>> iterators = entries.iterator();
            while (iterators.hasNext())
            {
                Map.Entry<String, String> entry = iterators.next();
                builder.add(entry.getKey(), entry.getValue());
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        }
        
        return new Request.Builder()
                .addHeader("content-type", "application/json;charset:utf-8")
                .url(url)
                .tag(url)
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        jsonObject.toJSONString()))
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
        Response response = postResponse(url, params);
        return response.body().toString();
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
        Call call = okHttpClient.newCall(buildRequest(url, params));
        Response response = call.execute();
        return response;
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
        Call call = okHttpClient.newCall(buildRequest(url, params));
        call.enqueue(callback);
    }
    
    public void cancel(String tag)
    {
        for (Call call:okHttpClient.dispatcher().runningCalls())
        {
            if (call.request().tag().equals(tag))
            {
                call.cancel();
            }
        }
        
        for (Call call:okHttpClient.dispatcher().queuedCalls())
        {
            if (call.request().tag().equals(tag))
            {
                call.cancel();
            }
        }
    }
    
    public void cancelAll()
    {
        okHttpClient.dispatcher().cancelAll();
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
        Call call = okHttpClient.newCall(buildRequest(url, params));
        call.enqueue(callback);
    }
}
