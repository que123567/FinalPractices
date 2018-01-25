package com.qiuzhonghao.finalpractices.base;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 2018/1/24 15:32,
 * Created by QiuZhongHao.
 */

/**
 * 封装了TextWatcher
 * 考虑到大部分情况下只需要监听onTextChanged这个状态,before和after经常空出来占用一大部分地方,故封装一下
 *
 */
public abstract class myTextWatcher implements TextWatcher {

    public abstract void onMyTextChanged(CharSequence s, int start, int before, int count);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onMyTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
