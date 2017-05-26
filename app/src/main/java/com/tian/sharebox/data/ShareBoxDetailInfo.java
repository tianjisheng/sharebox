package com.tian.sharebox.data;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/19
 * @describe 详细信息
 */

public class ShareBoxDetailInfo
{
    private String boxId = "";

    public static void main(String[] args)
    {
        int result = (int) (Math.random()* (9999 - 1000 + 1)) + 1000;
        System.out.println(result);
    }

    public boolean checkFormatValue(String panelSize)
    {
        if (panelSize == null)
        {
            return false;
        }
        panelSize = panelSize.trim();
        if (panelSize.equals(""))
        {
            return false;
        }
        try
        {
            float value = Float.valueOf(panelSize);
            if (value <= 0)
            {
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}
