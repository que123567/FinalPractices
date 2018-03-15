package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;

import butterknife.BindView;

import static com.google.common.base.Preconditions.checkNotNull;

public class AnswerDetailActivity extends BaseActivity {

    @BindView(R.id.tv_answer_detail_content)
    TextView mTvAnswerDetailContent;

    @Override
    public int getLayout() {
        return R.layout.activity_answer_detail;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        String str = bundle.getString("ArticleAnswerBean");
        checkNotNull(str);
        initArticleAnswerBean(str);
    }

    /**
     * 回答内容赋值
     *
     * @param str
     */
    private void initArticleAnswerBean(String str) {
        mTvAnswerDetailContent.setText(str);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
