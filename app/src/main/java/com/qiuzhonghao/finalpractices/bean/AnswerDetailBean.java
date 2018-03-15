package com.qiuzhonghao.finalpractices.bean;

/**
 * 2018/3/14 22:31,
 * Created by QiuZhongHao.
 * 回答->详情
 */

public class AnswerDetailBean {
    private String phone_number;
    private String answer_detail_title;
    private String answer_detail_time;
    private String answer_detail_id;
    private String answer_content;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAnswer_detail_title() {
        return answer_detail_title;
    }

    public void setAnswer_detail_title(String answer_detail_title) {
        this.answer_detail_title = answer_detail_title;
    }

    public String getAnswer_detail_time() {
        return answer_detail_time;
    }

    public void setAnswer_detail_time(String answer_detail_time) {
        this.answer_detail_time = answer_detail_time;
    }

    public String getAnswer_detail_id() {
        return answer_detail_id;
    }

    public void setAnswer_detail_id(String answer_detail_id) {
        this.answer_detail_id = answer_detail_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
