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

}
