package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.MainHomeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AnswerActivity extends BaseActivity {

    CommonAdapter<MainHomeBean> mMainHomeAdapter;
    List<MainHomeBean> mBeanList;

    @BindView(R.id.answer_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_answer_follow_num)
    TextView mTvFollowNum;
    @BindView(R.id.tv_answer_comment_num)
    TextView mTvCommentNum;
    @BindView(R.id.btn_answer_follow)
    Button mBtnFollow;
    @BindView(R.id.rv_answer)
    RecyclerView mRecyclerView;

    @Override
    public int getLayout() {
        return R.layout.activity_answer;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitle("如何评价在野外长跑比赛中??");
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle("如何评价在野外长跑比赛中?");

        }
        initData();
        initAdapter();
    }

    @OnClick({R.id.tv_answer_follow_num, R.id.tv_answer_comment_num, R.id.btn_answer_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_answer_follow_num:
                break;
            case R.id.tv_answer_comment_num:
                break;
            case R.id.btn_answer_follow:
                break;
        }
    }

    /**
     * 初始化首页数据
     */
    private void initData() {
        mBeanList = new ArrayList<>();
        MainHomeBean mainHomeBean = new MainHomeBean();
        mainHomeBean.setTitleTime("15分钟");
        mainHomeBean.setAuthor("肥肥鱼");
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
    }


    /**
     * 初始化首页适配器
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<MainHomeBean>(this, R.layout.item_main_home, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, MainHomeBean mainHomeBean, int position) {
                holder.setText(R.id.tv_main_home_time, "12分钟");
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }
}
