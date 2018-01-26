package com.qiuzhonghao.finalpractices.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.base.myTextWatcher;
import com.qiuzhonghao.finalpractices.ui.custom.ClearWriteEditText;
import com.qiuzhonghao.finalpractices.util.PhoneNumberUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.et_login_password)
    ClearWriteEditText etPassword;

    @BindView(R.id.et_login_phone)
    ClearWriteEditText etPhone;

    @BindView(R.id.login_iv_backgroud)
    ImageView ivBackground;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackGroundAnnimaiton(ivBackground);
        setEditTextWatcher();
        startActivity(MainActivity.class);
    }


    /**
     * 监听账号长度,达到11位时隐藏键盘
     */
    private void setEditTextWatcher() {
        etPhone.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    PhoneNumberUtil.hideSoftKeyBoard(LoginActivity.this, etPassword);
                }
            }
        });
    }


    /**
     * 背景图片左右缓慢移动
     *
     * @param ivBackground
     */
    private void setBackGroundAnnimaiton(final ImageView ivBackground) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                ivBackground.startAnimation(animation);
            }
        }, 200);
    }

    /**
     * 登录信息核对
     */
    @OnClick(R.id.btn_login)
    public void checkLoginInfo() {
        String phoneNumber = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            etPhone.setShakeAnimation();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            etPassword.setShakeAnimation();
            return;
        }

        if (phoneNumber.contains(" ")) {
            Toast.makeText(this, "账号不能包含空格", Toast.LENGTH_SHORT).show();
            etPhone.setShakeAnimation();
            return;
        }
        if (password.contains(" ")) {
            Toast.makeText(this, "密码不能包含空格", Toast.LENGTH_SHORT).show();
            etPassword.setShakeAnimation();
            return;
        }
        //TODO API登录成功
        startActivity(MainActivity.class);
    }

    /**
     * 注册页面
     */
    @OnClick(R.id.tv_login_register)
    public void doRegister() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivityForResult(intent, 10001);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 10001:
                    etPhone.setText(data.getStringExtra("RegPhone"));
                    etPassword.setText(data.getStringExtra("RegPassword"));
                    break;
                default:
                    break;

            }
        }
    }
}
