package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.UsersAddBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 2018/4/18 20:23,
 * Created by QiuZhongHao.
 */

public interface UserService {
    @GET("doUserAdd.php")
    Observable<List<UsersAddBean>> getUserInfo();

}
