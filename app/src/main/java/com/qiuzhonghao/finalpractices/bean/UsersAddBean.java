package com.qiuzhonghao.finalpractices.bean;

/**
 * 2018/4/18 19:19,
 * Created by QiuZhongHao.
 */

public class UsersAddBean {
    private String phone_number;
    private String head_image_url;
    private String vote_number;
    private String follow_number;
    private String location;
    private String brief_intro;
    private String nickname;


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getHead_image_url() {
        return head_image_url;
    }

    public void setHead_image_url(String head_image_url) {
        this.head_image_url = head_image_url;
    }

    public String getVote_number() {
        return vote_number;
    }

    public void setVote_number(String vote_number) {
        this.vote_number = vote_number;
    }

    public String getFollow_number() {
        return follow_number;
    }

    public void setFollow_number(String follow_number) {
        this.follow_number = follow_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
