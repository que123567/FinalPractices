package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AnswerDetailActivity extends BaseActivity {

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

    @Override
    public int getLayout() {

        return R.layout.activity_answer_detail;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        ArticleAnswerBean articleAnswerBean = (ArticleAnswerBean) bundle.get("ArticleAnswerBean");
        String headUrl = bundle.getString("HeadUrl");
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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
