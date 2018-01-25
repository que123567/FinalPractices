package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.base.myTextWatcher;
import com.qiuzhonghao.finalpractices.ui.custom.ClearWriteEditText;
import com.qiuzhonghao.finalpractices.util.PhoneNumberUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_register_username)
    ClearWriteEditText mEtUsername;
    @BindView(R.id.et_register_phone)
    ClearWriteEditText mEtPhone;
    @BindView(R.id.et_register_password)
    ClearWriteEditText mEtPassword;
    @BindView(R.id.et_register_password_again)
    ClearWriteEditText mEtPasswordAgain;
    @BindView(R.id.btn_register_register)
    Button mBtnRegister;
    @BindView(R.id.tv_register_login)
    TextView mTvLogin;
    @BindView(R.id.iv_register_background)
    ImageView mIvBackground;
    @BindView(R.id.tv_register_forget)
    TextView mTvForget;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackGroundAnnimaiton(mIvBackground);
        setEditTextWatcher();

    }

    /**
     * 添加电话
     */
    private void setEditTextWatcher() {
        mEtPhone.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    if (PhoneNumberUtil.isPhoneNumber(s.toString().trim())) {
                        String mPhone = s.toString().trim();
                        //TODO API1:查看账号是否存在,默认否

                        PhoneNumberUtil.hideSoftKeyBoard(RegisterActivity.this, mEtPhone);
                    } else {
                        Toast.makeText(RegisterActivity.this, "非法手机号", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn_register_register, R.id.tv_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_register:
                checkRegisterInfo();
                break;
            case R.id.tv_register_login:
                startActivity(LoginActivity.class);
                break;
        }
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
                Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.translate_anim);
                ivBackground.startAnimation(animation);
            }
        }, 200);
    }

    /**
     * 核对注册信息
     */
    private void checkRegisterInfo() {
        if (checkEmpty(mEtUsername, "昵称"))
            return;
        if (checkContainBlank(mEtUsername, "昵称"))
            return;
        if (checkEmpty(mEtPassword, "密码"))
            return;
        if (checkContainBlank(mEtPassword, "密码"))
            return;
        if (checkEmpty(mEtPasswordAgain, "确认密码"))
            return;
        if (checkContainBlank(mEtPasswordAgain, "确认密码"))
            return;
        if (!checkIsSame(mEtPassword, mEtPasswordAgain))
            return;
        //TODO API注册成功,添加账号
        Intent intent = new Intent();
        intent.putExtra("RegPhone", mEtPhone.getText().toString());
        intent.putExtra("RegPassword", mEtPassword.getText().toString());
        setResult(RESULT_OK, intent);
        showToast("注册成功");
        finish();
    }

    /**
     * 查空
     *
     * @param editText
     * @param content
     * @return
     */
    private boolean checkEmpty(ClearWriteEditText editText, String content) {
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            Toast.makeText(this, content + "不能为空", Toast.LENGTH_SHORT).show();
            editText.setShakeAnimation();
            return true;
        }
        return false;
    }

    /**
     * 查是否含空格
     *
     * @param editText
     * @param content
     * @return
     */
    private boolean checkContainBlank(ClearWriteEditText editText, String content) {
        if (editText.getText().toString().contains(" ")) {
            Toast.makeText(this, content + "不能包含空格", Toast.LENGTH_SHORT).show();
            editText.setShakeAnimation();
            return true;
        }
        return false;
    }


    private boolean checkIsSame(ClearWriteEditText editText1, ClearWriteEditText editText2) {
        if (editText1 != null && editText2 != null) {
            if (!editText1.getText().toString().equals(editText2.getText().toString())) {
                Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        return false;
    }
}
