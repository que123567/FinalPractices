package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;
import com.qiuzhonghao.finalpractices.bean.MainHomeArticleBean;
import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/2/7 11:21,
 * Created by QiuZhongHao.
 */

public interface ArticleService {
    @GET("getArticleInfo.php")
    Observable<List<MainHomeArticleBean>> getArticleInfo();

    @GET("getSearchInfo.php")
    Observable<List<MainHomeArticleBean>> getSearchInfo(@Query("search") String str);

    @GET("getDetailInfo.php")
    Observable<List<ArticleAnswerBean>> getDetailInfo(@Query("title") String id);

    @GET("doQuestionUpload.php")
    Observable<ResultCodeBean> doQuestionUpload(@Query("title") String title, @Query("content") String content, @Query("author") String author, @Query("flag") String flag);
}
