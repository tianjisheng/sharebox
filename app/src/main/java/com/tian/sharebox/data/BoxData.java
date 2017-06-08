package com.tian.sharebox.data;

import java.io.Serializable;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/19
 * @describe 每个box信息
 */

public class BoxData implements Serializable
{
    private String containerId = "";
    private String name = "";
    private String address = "";
    private double latitude = 0d;
    private double longitude = 0d;

    public String getContainerId()
    {
        return containerId;
    }

    public void setContainerId(String containerId)
    {
        this.containerId = containerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}
