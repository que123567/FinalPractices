package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/1/31 09:11,
 * Created by QiuZhongHao.
 */

public interface RegisterService {
    @GET("doUserRegister.php")
    Observable<ResultCodeBean> doRegister(@Query("phone_number") String phoneNumber, @Query("password") String password, @Query("nickname") String nickname);


}
