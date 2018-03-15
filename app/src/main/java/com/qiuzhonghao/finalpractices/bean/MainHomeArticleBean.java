package com.qiuzhonghao.finalpractices.bean;

import java.io.Serializable;

/**
 * 2018/2/7 11:16,
 * Created by QiuZhongHao.
 */

public class MainHomeArticleBean implements Serializable {
    String article_name;
    String article_time;
    String article_vote_number;
    String article_comment_number;
    String article_id;
    String article_author;
    String article_author_head;
    String article_content;

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    public String getArticle_vote_number() {
        return article_vote_number;
    }

    public void setArticle_vote_number(String article_vote_number) {
        this.article_vote_number = article_vote_number;
    }

    public String getArticle_comment_number() {
        return article_comment_number;
    }

    public void setArticle_comment_number(String article_comment_number) {
        this.article_comment_number = article_comment_number;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_author_head() {
        return article_author_head;
    }

    public void setArticle_author_head(String article_author_head) {
        this.article_author_head = article_author_head;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }
}
