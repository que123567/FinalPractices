package com.qiuzhonghao.finalpractices.util;

import com.qiuzhonghao.finalpractices.constant.API;

/**
 * 2018/4/15 16:06,
 * Created by QiuZhongHao.
 */

public class UserUtil {
    public static String getUserHeadURL(String authorName) {
        String tmpFilePath = "final/file/tmpFile/";
        return API.BASE_URL + tmpFilePath + authorName + ".png";
    }
}
