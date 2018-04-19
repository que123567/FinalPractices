package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.NotifyBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/2/5 16:03,
 * Created by QiuZhongHao.
 */

public interface NotifyService {
    @GET("doUserNotify.php")
    Observable<NotifyBean> doNotify(@Query("notify_title") String title, @Query("notify_from") String from, @Query("notify_to") String to);

    @GET("getNotice.php")
    Observable<List<NotifyBean>> getNotify(@Query("notify_to") String author);
}
