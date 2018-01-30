package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 2018/1/30 16:31,
 * Created by QiuZhongHao.
 */

public interface LoginService {
    @GET("CheckUserExist.php")
    Call<ResponseBody> getLoginInfo(@Query("phone_number") String phoneNumber);
}
