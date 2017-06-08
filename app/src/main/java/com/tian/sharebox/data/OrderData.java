package com.tian.sharebox.data;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class OrderData
{
    private String orderId = "";
    private String goodsName = "";
    private int stateCode = 0;
    private String orderState = "";
    private String startTime = "";
    private String endTime = "";
    private String[] stateName = {"异常","下单","执行中","取消","待支付","支付完成"};

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public int getStateCode()
    {
        return stateCode;
    }

    public void setStateCode(int stateCode)
    {
        this.stateCode = stateCode;
    }

    public String getOrderState()
    {
        if(stateCode>5)
        {
            return "未知";
        }
        return stateName[stateCode];
    }

    public void setOrderState(String orderState)
    {
        this.orderState = orderState;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}
