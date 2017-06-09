package com.tian.sharebox.network;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/1
 * @describe
 */

public class NetworkConfig
{
//    public static final String baseUrl = "http://119.29.195.138:8000/greenbox";
public static final String baseUrl = "http://www.baidu.com";
    /////////////////////////
    /**
     * 登陆,post,[username,password]
     * 返回：state，token，msg
     */
    public static final String login = "user/login";
    /**
     * 退出登陆，暂不实现
     */
    public static final String logout = "user/logout";
    ////////////////////////////
    /**
     * 借物（全归还），post,[token,goods_sn]
     * 返回：order_sn,order_state,state,msg
     */
    public static final String add = "borrow/full/app/order/add";
    ///////////////////////////
    /**
     * 查询附近可用物品，post，[category_id]
     * 返回：state,msg,goods_list[container_id,name,address,remain_total,latitude,longitude]
     */
    public static final String goodsNearby = "goods/nearby";
    /**
     * 查看物品详情，get,[goods_id]
     * 返回：state，msg,category,borrowed_times
     */
    public static final String goodsDetail = "goods/detail";
    //////////////////////////
    /**
     * 查询附近储物柜,post,[latitude,longitude,range]
     * 返回state,data,msg,data[container_id,name,address,latitude,longitude]
     */
    public static final String containerNearby = "container/nearby";
    /**
     * 查询单个储物柜，get,[container_id]
     * 返回：state，name,address,latitude,longitude,detail_list[category,remain_total],msg
     */
    public static final String containerSummary = "container/summary";
    //////////////////////////
    /**
     * 获取用户订单,get,[token,page_num]
     * 返回：total，page_num,order_list[goods_name,start_time,finish_time,order_state]
     */
    public static final String orderAll = "order/all";
    /**
     * 获取订单详情，get,[token,order_sn]
     * 返回：goods_category,start_time,finish_time,order_state,distance,borrow_container_id,return_container_id
     * cost,state,msg
     */
    public static final String orderDetail = "order/detail";
    /**
     * 取消订单，post,[token,order_sn]
     * 返回：state,msg
     */
    public static final String orderCancel = "order/cancel";
    /**
     * 支付订单
     */
    public static final String orderPay = "order/pay";
    
    public static String buildUrl(String... urls)
    {
        String res = "";
        for (String url:urls)
        {
            res += url;
        }
        return res;
    }
    
}
