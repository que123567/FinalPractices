package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/4/13 22:04,
 * Created by QiuZhongHao.
 */

public interface QuestionService {
    @GET("doQuestionAnswerAdd.php")
    Observable<ResultCodeBean> doAnswerAdd(@Query("author") String author, @Query("title") String title, @Query("content") String content);

    @GET("doAnswerDown.php")
    Observable<ResultCodeBean> doAnswerDown(@Query("fromAuthor") String fromAuthor, @Query("toAuthor") String toAuthor, @Query("article") String article);

    @GET("doAnswerUp.php")
    Observable<ResultCodeBean> doAnswerUp(@Query("fromAuthor") String fromAuthor, @Query("toAuthor") String toAuthor, @Query("article") String article);

    @GET("doGetAnswerNowScore.php")
    Observable<ResultCodeBean> getNowScore(@Query("toAuthor") String toAuthor, @Query("article") String article);

    @GET("doQuestionUserScore.php")
    Observable<ResultCodeBean> getRecord(@Query("fromAuthor") String fromAuthor, @Query("toAuthor") String toAuthor, @Query("article") String article);

    @GET("getReputation.php")
    Observable<ResultCodeBean> getReputation(@Query("author") String author);

}
