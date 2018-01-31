package com.qiuzhonghao.finalpractices.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 2018/1/31 10:16,
 * Created by QiuZhongHao.
 */

public class RxService {
    static RxService mMyRetroFit;

    private RxService() {
    }

    public static Retrofit getRetrofitInstance(String url) {
        if (mMyRetroFit == null)
            mMyRetroFit = new RxService();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }

}
