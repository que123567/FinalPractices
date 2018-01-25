package com.qiuzhonghao.finalpractices.bean;

/**
 * 2018/1/25 16:36,
 * Created by QiuZhongHao.
 */

public class MainHomeBean {
    private String title;// 标题
    private String author;// 作者/推荐人
    private String titleTime;// 更新时间
    private String breifIntro;// 简介
    private String voteNumber;// 点赞数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitleTime() {
        return titleTime;
    }

    public void setTitleTime(String titleTime) {
        this.titleTime = titleTime;
    }

    public String getBreifIntro() {
        return breifIntro;
    }

    public void setBreifIntro(String breifIntro) {
        this.breifIntro = breifIntro;
    }

    public String getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(String voteNumber) {
        this.voteNumber = voteNumber;
    }
}
