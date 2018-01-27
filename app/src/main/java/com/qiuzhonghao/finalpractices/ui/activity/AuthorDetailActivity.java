package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AuthorDetailActivity extends BaseActivity {

    @BindView(R.id.author_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_author_background)
    ImageView mIvAuthorBackground;
    @BindView(R.id.iv_author_head)
    CircleImageView mIvAuthorHead;
    @BindView(R.id.tv_author_name)
    TextView mTvAuthorName;
    @BindView(R.id.tv_author_brief_intro)
    TextView mTvAuthorBriefIntro;
    @BindView(R.id.btn_author_follow)
    Button mBtnAuthorFollow;
    @BindView(R.id.btn_author_chat)
    Button mBtnAuthorChat;
    @BindView(R.id.rv_author_works)
    RecyclerView mRecyclerview;


    @Override
    public int getLayout() {
        return R.layout.activity_author_detail;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle("");
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        toolbarClickListen();

    }

    /**
     * toolbar点击监听
     */
    private void toolbarClickListen() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick({R.id.iv_author_background, R.id.iv_author_head, R.id.btn_author_follow, R.id.btn_author_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_author_background:
                break;
            case R.id.iv_author_head:
                break;
            case R.id.btn_author_follow:
                break;
            case R.id.btn_author_chat:
                break;
        }
    }
}
