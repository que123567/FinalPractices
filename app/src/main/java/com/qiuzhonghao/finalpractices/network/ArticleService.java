package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;
import com.qiuzhonghao.finalpractices.bean.MainHomeArticleBean;

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
    Observable<List<ArticleAnswerBean>> getDetailInfo(@Query("articleId") String id);
}
