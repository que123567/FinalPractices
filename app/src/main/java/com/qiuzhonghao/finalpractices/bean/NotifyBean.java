package com.qiuzhonghao.finalpractices.bean;

/**
 * 2018/4/18 21:51,
 * Created by QiuZhongHao.
 */

public class NotifyBean {
    private String notify_title;
    private String notify_from;
    private String notify_to;
    private String notify_time;
    private String result_code;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getNotify_title() {
        return notify_title;
    }

    public void setNotify_title(String notify_title) {
        this.notify_title = notify_title;
    }

    public String getNotify_from() {
        return notify_from;
    }

    public void setNotify_from(String notify_from) {
        this.notify_from = notify_from;
    }

    public String getNotify_to() {
        return notify_to;
    }

    public void setNotify_to(String notify_to) {
        this.notify_to = notify_to;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }
}
