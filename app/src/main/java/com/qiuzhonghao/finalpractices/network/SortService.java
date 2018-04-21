package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/4/21 10:32,
 * Created by QiuZhongHao.
 */

public interface SortService {
    @GET("doSortByScore.php")
    Observable<List<ArticleAnswerBean>> doSortByScore(@Query("title") String article);

    @GET("doSortByTime.php")
    Observable<List<ArticleAnswerBean>> doSortByTime(@Query("title") String article);

}
