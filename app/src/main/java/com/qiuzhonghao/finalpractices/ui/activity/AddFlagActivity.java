package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.ArticleService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.ui.custom.ClearWriteEditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddFlagActivity extends BaseActivity {

    @BindView(R.id.btn_question_submit)
    Button mBtnQuestionSubmit;
    @BindView(R.id.et_add_flag)
    ClearWriteEditText mEtAddFlag;

    String title;
    String content;

    @Override
    public int getLayout() {
        return R.layout.activity_add_flaig;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        title = bundle.getString("TITLE");
        content = bundle.getString("CONTENT");
        checkNotNull(title);
        checkNotNull(content);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_question_submit)
    public void onViewClicked() {
        SharedPreferences share = getSharedPreferences("TOKEN", MODE_PRIVATE);
        String author = share.getString("UserNickName", "smagu");
        String flag = mEtAddFlag.getText().toString();
        checkNotNull(false);
        upLoadQuestion(title, content, author, flag);

    }

    private void upLoadQuestion(final String title, final String content, String author, final String flag) {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.doQuestionUpload(title, content, author, flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (title.trim().equals("") || content.trim().equals("") || flag.trim().equals("")) {
                            Toast.makeText(AddFlagActivity.this, "问题不完整,提交失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (resultCodeBean.getResult_code() == 0) {
                            Toast.makeText(AddFlagActivity.this, "提问成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddFlagActivity.this, "提问失败,存在相关问题", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(AddFlagActivity.this, "异常", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
