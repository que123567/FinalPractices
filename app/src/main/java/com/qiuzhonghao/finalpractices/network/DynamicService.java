package com.qiuzhonghao.finalpractices.network;

import com.qiuzhonghao.finalpractices.bean.DynamicBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 2018/2/1 16:37,
 * Created by QiuZhongHao.
 */

public interface DynamicService {
    @GET("getDynamicInfo.php")
    Observable<List<DynamicBean>> getDynamicInfo();
}
