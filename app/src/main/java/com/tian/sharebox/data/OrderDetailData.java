package com.tian.sharebox.data;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/6/6
 * @describe
 */

public class OrderDetailData extends OrderData
{
    private float cost = 0.00f;
    private float distance = 0.00f;
    private String borrowContainerId = "";
    private String returnContainerId = "";

    public float getCost()
    {
        return cost;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }

    public String getBorrowContainerId()
    {
        return borrowContainerId;
    }

    public void setBorrowContainerId(String borrowContainerId)
    {
        this.borrowContainerId = borrowContainerId;
    }

    public String getReturnContainerId()
    {
        return returnContainerId;
    }

    public void setReturnContainerId(String returnContainerId)
    {
        this.returnContainerId = returnContainerId;
    }
}
