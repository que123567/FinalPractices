package com.qiuzhonghao.finalpractices.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private View mContextView = null;

    /**
     * [绑定contentView]
     */
    public abstract int getLayout();


    /**
     * [获得intent参数]
     *
     * @param bundle
     */
    protected abstract void getIntentParams(Bundle bundle);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextView = LayoutInflater.from(this).inflate(getLayout(), null);
        setContentView(mContextView);
        ButterKnife.bind(this);
        Log.d("BaseActivity", TAG);
        Bundle bundle = getIntent().getExtras();
        getIntentParams(bundle);
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    protected void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 吐司封装,默认为short
     *
     * @param content
     */
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }


}
