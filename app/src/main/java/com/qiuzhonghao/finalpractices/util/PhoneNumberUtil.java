package com.qiuzhonghao.finalpractices.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2018/1/24 15:20,
 * Created by QiuZhongHao.
 */

public class PhoneNumberUtil {
    /**
     * 手机号正则表达式
     **/
    public final static String MOBLIE_PHONE_PATTERN = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[7])|(17[0|3|6|7|8]))\\d{8}$";

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(MOBLIE_PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param editText
     */
    public static void showSoftKeyBoard(Context context, EditText editText) {
        if (editText == null)
            return;
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    /**
     * 隐藏软键盘
     * (应用场景①登录界面:输入账号满11位时(电话号码)隐藏软键盘)
     *
     * @param context
     * @param editText
     */
    public static void hideSoftKeyBoard(Context context, EditText editText) {
        if (editText == null)
            return;
        editText.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
