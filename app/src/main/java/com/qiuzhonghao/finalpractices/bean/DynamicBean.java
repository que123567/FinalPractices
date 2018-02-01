package com.qiuzhonghao.finalpractices.bean;

/**
 * 2018/2/1 16:38,
 * Created by QiuZhongHao.
 */

public class DynamicBean {
    String dynamic_author; //作者
    String dynamic_publish_time;//发布时间
    String dynamic_content;//标题
    String dynamic_vote_number;//动态点赞数
    String dynamic_comment_number;//动态评论数
    String dynamic_author_head_url;//作者头像url
    String dynamic_id;//动态id
    String dynamic_author_phone;//作者电话

    public String getDynamic_author() {
        return dynamic_author;
    }

    public void setDynamic_author(String dynamic_author) {
        this.dynamic_author = dynamic_author;
    }

    public String getDynamic_publish_time() {
        return dynamic_publish_time;
    }

    public void setDynamic_publish_time(String dynamic_publish_time) {
        this.dynamic_publish_time = dynamic_publish_time;
    }

    public String getDynamic_content() {
        return dynamic_content;
    }

    public void setDynamic_content(String dynamic_content) {
        this.dynamic_content = dynamic_content;
    }

    public String getDynamic_vote_number() {
        return dynamic_vote_number;
    }

    public void setDynamic_vote_number(String dynamic_vote_number) {
        this.dynamic_vote_number = dynamic_vote_number;
    }

    public String getDynamic_comment_number() {
        return dynamic_comment_number;
    }

    public void setDynamic_comment_number(String dynamic_comment_number) {
        this.dynamic_comment_number = dynamic_comment_number;
    }

    public String getDynamic_author_head_url() {
        return dynamic_author_head_url;
    }

    public void setDynamic_author_head_url(String dynamic_author_head_url) {
        this.dynamic_author_head_url = dynamic_author_head_url;
    }

    public String getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(String dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getDynamic_author_phone() {
        return dynamic_author_phone;
    }

    public void setDynamic_author_phone(String dynamic_author_phone) {
        this.dynamic_author_phone = dynamic_author_phone;
    }

    @Override
    public String toString() {
        return "DynamicBean{" +
                "dynamic_author='" + dynamic_author + '\'' +
                ", dynamic_publish_time='" + dynamic_publish_time + '\'' +
                ", dynamic_content='" + dynamic_content + '\'' +
                ", dynamic_vote_number='" + dynamic_vote_number + '\'' +
                ", dynamic_comment_number='" + dynamic_comment_number + '\'' +
                ", dynamic_author_head_url='" + dynamic_author_head_url + '\'' +
                ", dynamic_id='" + dynamic_id + '\'' +
                ", dynamic_author_phone='" + dynamic_author_phone + '\'' +
                '}';
    }
}
