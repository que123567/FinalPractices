package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;
import com.qiuzhonghao.finalpractices.bean.ResultCodeBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.QuestionService;
import com.qiuzhonghao.finalpractices.network.RxService;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AnswerDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_answer_detail_content)
    TextView mTvAnswerDetailContent;
    @BindView(R.id.iv_answer_detail_head)
    CircleImageView mCircleImageView;
    @BindView(R.id.tv_answer_detail_name)
    TextView mTvAnswerDetailName;
    @BindView(R.id.tv_answer_detail_brief)
    TextView mTvAnswerDetailBrief;
    @BindView(R.id.tv_answer_detail_time)
    TextView mTvAnswerDetailTime;
    @BindView(R.id.btn_answer_up)
    Button mBtnAnswerUp;
    @BindView(R.id.btn_answer_down)
    Button mBtnAnswerDown;
    @BindView(R.id.btn_answer_collect)
    Button mBtnAnswerCollect;
    @BindView(R.id.btn_answer_comment)
    Button mBtnAnswerComment;
    @BindView(R.id.tv_answer_detail_title)
    TextView mTvAnswerDetailTitle;

    String author;
    ArticleAnswerBean articleAnswerBean;
    String title;
    @BindView(R.id.btn_answer_detail_follow)
    Button mBtnAnswerDetailFollow;
    @BindView(R.id.ll_answer_detail)
    LinearLayout mLlAnswerDetail;

    @Override
    public int getLayout() {
        return R.layout.activity_answer_detail;
    }

    private String userScore;

    private String nowScore;

    @Override
    protected void getIntentParams(Bundle bundle) {
        articleAnswerBean = (ArticleAnswerBean) bundle.get("ArticleAnswerBean");
        String headUrl = bundle.getString("HeadUrl");
        title = bundle.getString("ArticleTitle");
        SharedPreferences share = getSharedPreferences("TOKEN", MODE_PRIVATE);
        author = share.getString("UserNickName", "smagu");
        initArticleAnswerBean(articleAnswerBean, headUrl);
    }


    /**
     * 回答内容赋值
     *
     * @param bean
     * @param headUrl
     */
    private void initArticleAnswerBean(ArticleAnswerBean bean, String headUrl) {
        mTvAnswerDetailContent.setText(bean.getArticle_detail_content());
        mTvAnswerDetailName.setText(bean.getArticle_detail_author_name());
        Glide.with(this).load(headUrl).into(mCircleImageView);
        mTvAnswerDetailTitle.setText(title);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNowScore();//更新当前分数
        getReputation();
        mBtnAnswerUp.setOnClickListener(this);
        mBtnAnswerDown.setOnClickListener(this);
        mBtnAnswerUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!mBtnAnswerUp.isEnabled() && mBtnAnswerDown.isEnabled()) {//不能点赞 可以反对
//                    doQuestionDown();//取消赞
//                    return;
//                }
//                if (mBtnAnswerUp.isEnabled() && !mBtnAnswerDown.isEnabled()) {//没点过赞,点了反对
//                    doQuestionUp();//双倍赞,抵消一次反对
//                    doQuestionUp();
//                    return;
//                }
                doQuestionUp();
            }
        });
        mBtnAnswerDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doQuestionDown();

//                if (mBtnAnswerUp.isEnabled() && !mBtnAnswerDown.isEnabled()) {//能点赞 不可以反对
//                    doQuestionUp();//取消反对
//                    return;
//                }
//                if (!mBtnAnswerUp.isEnabled() && mBtnAnswerDown.isEnabled()) {//不能点赞,可以反对
//                    doQuestionDown();//双倍反对,抵消一次点赞
//                    doQuestionDown();
//                    return;
//                }
//                getNowScore();
            }
        });
    }


    private void doQuestionUp() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService questionService = retrofit.create(QuestionService.class);
        final String toAuthor = mTvAnswerDetailName.getText().toString();
        questionService.doAnswerUp(author, toAuthor, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getResult_code() == 0) {
//                            String score = resultCodeBean.getScore();
//                            mBtnAnswerCollect.setText("分数:" + score);
                            getRecord();
                            getNowScore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    private void doQuestionDown() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService questionService = retrofit.create(QuestionService.class);
        final String toAuthor = mTvAnswerDetailName.getText().toString();
        questionService.doAnswerDown(author, toAuthor, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getResult_code() == 0) {
                            getRecord();
                            getNowScore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

    }


    private void getNowScore() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService questionService = retrofit.create(QuestionService.class);
        final String toAuthor = mTvAnswerDetailName.getText().toString();
        questionService.getNowScore(toAuthor, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getScore() != null) {
                            nowScore = resultCodeBean.getScore();
                            mBtnAnswerCollect.setText("分数:" + nowScore);
                        } else {
                            mBtnAnswerCollect.setText("分数:" + 0);

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    private void getRecord() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService questionService = retrofit.create(QuestionService.class);
        final String toAuthor = mTvAnswerDetailName.getText().toString();
        questionService.getRecord(author, toAuthor, title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getScore() != null) {
                            userScore = resultCodeBean.getScore();
                            int score = Integer.valueOf(userScore);
                            if (score < 0) { //点过反对
                                mBtnAnswerUp.setEnabled(true);
                                mBtnAnswerDown.setEnabled(false);
                            } else if (score > 0) {
                                mBtnAnswerUp.setEnabled(false);
                                mBtnAnswerDown.setEnabled(true);
                            } else if (score == 0) {
                                mBtnAnswerUp.setEnabled(true);
                                mBtnAnswerDown.setEnabled(true);
                            }
                        } else {//没有做过任何操作,不做操作

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

    }

    private void getReputation() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.QUESTION);
        QuestionService questionService = retrofit.create(QuestionService.class);
        questionService.getReputation(author)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultCodeBean>() {
                    @Override
                    public void accept(ResultCodeBean resultCodeBean) throws Exception {
                        if (resultCodeBean.getReputation() != null) {
                            if (resultCodeBean.getReputation().equals("0")) {
                                mBtnAnswerUp.setEnabled(false);
                                mBtnAnswerDown.setEnabled(false);
                            } else {
                                getRecord();//根据点赞记录 更新按钮状态
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }
}
