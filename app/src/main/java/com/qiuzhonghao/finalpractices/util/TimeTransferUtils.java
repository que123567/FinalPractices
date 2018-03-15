package com.qiuzhonghao.finalpractices.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by smaug on 2018/2/7.
 */

public class TimeTransferUtils {

    public static String timeStampCalculator(long seconds) { //传入时间戳，返回距离时间
        seconds /= 1000;
        String currentTime = currentTimeStamp();
        long deltaTimeSeconds = (Long.parseLong(currentTime) - seconds);
        long deltaTimeMinute = deltaTimeSeconds / 60;
        long deltaTimeHours = deltaTimeMinute / 60;
        long deltaTimeDay = deltaTimeHours / 24;
        String result;
        if (deltaTimeSeconds <= 60) {
            result = "刚刚";
        } else if (deltaTimeMinute <= 60) {
            result = deltaTimeMinute + "分钟前";
        } else if (deltaTimeHours <= 24) {
            result = deltaTimeHours + "小时前";
        } else {
            result = deltaTimeDay + "天前";
        }
        return result;
    }

    /**
     *
     * @param date
     * @param separator 时间分隔符:比如冒号,减号等;
     * @return
     */
    public static String dateCalculator(String date, String separator) {
        String dateFormat = String.format("yyyy%sMM%sdd HH:mm:ss", separator, separator);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String result;
        String currentTime = currentTimeStamp(); //获取当前时间戳
        String date2TimeStamp;
        try {
            date2TimeStamp = String.valueOf(sdf.parse(date).getTime() / 1000);//日期转换为时间戳(单位：秒)
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        long deltaTimeSeconds = (Long.parseLong(currentTime) - Long.parseLong(date2TimeStamp));
        long deltaTimeMinute = deltaTimeSeconds / 60;
        long deltaTimeHours = deltaTimeMinute / 60;
        long deltaTimeDay = deltaTimeHours / 24;
        if (deltaTimeSeconds <= 60) {
            result = "刚刚";
        } else if (deltaTimeMinute <= 60) {
            result = deltaTimeMinute + "分钟前";
        } else if (deltaTimeHours <= 24) {
            result = deltaTimeHours + "小时前";
        } else {
            result = deltaTimeDay + "天前";
        }
        return result;
    }


    private static String currentTimeStamp() { //获取当前时间戳
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

}
