package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/1/30 16:31,
 * Created by QiuZhongHao.
 */

public interface LoginService {
    @GET("CheckUserExist.php")
    Observable<ResultCodeBean> checkUserExist(@Query("phone_number") String phoneNumber);

    @GET("checkUserPassword.php")
    Observable<ResultCodeBean> checkUserPassword(@Query("phone_number") String phoneNumber, @Query("password") String password);
}
