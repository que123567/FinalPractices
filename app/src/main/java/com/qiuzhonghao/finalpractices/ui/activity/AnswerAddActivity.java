package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.QuestionService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.ui.custom.ClearWriteEditText;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AnswerAddActivity extends BaseActivity {


    @BindView(R.id.tv_answer_add_title)
    TextView mTvAnswerAddTitle;
    @BindView(R.id.add_answer_close)
    Button mAddAnswerClose;
    @BindView(R.id.add_answer_submit)
    Button mAddAnswerSubmit;
    @BindView(R.id.et_add_quesion)
    ClearWriteEditText mEtAddQuesion;

    private String titleName;
    private String author;

    @Override
    public int getLayout() {
        return R.layout.activity_answer_add;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        titleName = bundle.getString("TITLE");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvAnswerAddTitle.setText(titleName);
        SharedPreferences share = getSharedPreferences("TOKEN", MODE_PRIVATE);
        author = share.getString("UserNickName", "null");

    }

    @OnClick({R.id.add_answer_close, R.id.add_answer_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_answer_close:
                finish();
                break;
            case R.id.add_answer_submit:
                String content = mEtAddQuesion.getText().toString();//回答内容
                addAnswers(author, titleName, content);
                break;
        }
    }

    private void addAnswers(String author, String titleName, String content) {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService articleService = retrofit.create(QuestionService.class);
        articleService.doAnswerAdd(author, titleName, content)//TODO:根据文章id获取信息
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getResult_code() == 0) {
                            Toast.makeText(AnswerAddActivity.this, "回答添加成功", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(AnswerAddActivity.this, "添加失败,回答已添加,", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(AnswerAddActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
