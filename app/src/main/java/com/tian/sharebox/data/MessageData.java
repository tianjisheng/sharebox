package com.tian.sharebox.data;

/**
 * @author jisheng ,tianjisheng@skyworth.com
 * @date 2017/5/26
 * @describe
 */

public class MessageData
{
    private int id = -1;
    private String messageTitle = "";
    private String messageBody = "";
    private String messageIconPath = "";
    private String messageUrl = "";
    private String messageDate = "";

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getMessageTitle()
    {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle)
    {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody()
    {
        return messageBody;
    }

    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }

    public String getMessageIconPath()
    {
        return messageIconPath;
    }

    public void setMessageIconPath(String messageIconPath)
    {
        this.messageIconPath = messageIconPath;
    }

    public String getMessageUrl()
    {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl)
    {
        this.messageUrl = messageUrl;
    }

    public String getMessageDate()
    {
        return messageDate;
    }

    public void setMessageDate(String messageDate)
    {
        this.messageDate = messageDate;
    }
}
