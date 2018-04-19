package com.qiuzhonghao.finalpractices.bean;

import java.io.Serializable;

/**
 * 2018/3/14 16:18,
 * Created by QiuZhongHao.
 */

public class ArticleAnswerBean implements Serializable {
    private String article_detail_author_phone;
    private String article_detail_author_name;
    private String article_detail_vote_number;
    private String article_detail_comment_number;
    private String article_detail_time;
    private String article_detail_content;
    private String article_detail_id;

    public String getArticle_detail_author_name() {
        return article_detail_author_name;
    }

    public void setArticle_detail_author_name(String article_detail_author_name) {
        this.article_detail_author_name = article_detail_author_name;
    }

    public String getArticle_detail_author_phone() {
        return article_detail_author_phone;
    }

    public void setArticle_detail_author_phone(String article_detail_author_phone) {
        this.article_detail_author_phone = article_detail_author_phone;
    }

    public String getArticle_detail_vote_number() {
        return article_detail_vote_number;
    }

    public void setArticle_detail_vote_number(String article_detail_vote_number) {
        this.article_detail_vote_number = article_detail_vote_number;
    }

    public String getArticle_detail_comment_number() {
        return article_detail_comment_number;
    }

    public void setArticle_detail_comment_number(String article_detail_comment_number) {
        this.article_detail_comment_number = article_detail_comment_number;
    }

    public String getArticle_detail_time() {
        return article_detail_time;
    }

    public void setArticle_detail_time(String article_detail_time) {
        this.article_detail_time = article_detail_time;
    }

    public String getArticle_detail_content() {
        return article_detail_content;
    }

    public void setArticle_detail_content(String article_detail_content) {
        this.article_detail_content = article_detail_content;
    }

    public String getArticle_detail_id() {
        return article_detail_id;
    }

    public void setArticle_detail_id(String article_detail_id) {
        this.article_detail_id = article_detail_id;
    }
}
