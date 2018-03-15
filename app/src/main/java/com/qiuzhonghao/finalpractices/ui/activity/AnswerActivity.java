package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.ArticleAnswerBean;
import com.qiuzhonghao.finalpractices.bean.MainHomeArticleBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.ArticleService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.util.TimeTransferUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

public class AnswerActivity extends BaseActivity {

    CommonAdapter<ArticleAnswerBean> mMainHomeAdapter;
    List<ArticleAnswerBean> mBeanList;

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
    @BindView(R.id.tv_answer_title)
    TextView mTvAnswerTitle;
    @BindView(R.id.tv_answer_descript)
    TextView mTvAnswerDescript;

    @Override
    public int getLayout() {
        return R.layout.activity_answer;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        MainHomeArticleBean articleBean = (MainHomeArticleBean) bundle.get("MainHomeBean");
        initArticleAnswerBean(articleBean);
    }

    /**
     * 初始化问答标题栏
     *
     * @param articleBean
     */
    private void initArticleAnswerBean(MainHomeArticleBean articleBean) {
        checkNotNull(articleBean);
        mTvAnswerDescript.setText(articleBean.getArticle_content());//简介
        mTvAnswerTitle.setText(articleBean.getArticle_name());//标题
        mTvCommentNum.setText(articleBean.getArticle_comment_number() + R.string.comment);//评论数
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle("");
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
        getArticleDetailInfo();
    }

    private void getArticleDetailInfo() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.ARTICLE);
        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.getDetailInfo("1")//TODO:根据文章id获取信息
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ArticleAnswerBean>>() {
                    @Override
                    public void accept(List<ArticleAnswerBean> articleAnswerBeanList) throws Exception {
                        mBeanList.addAll(articleAnswerBeanList);
                        mMainHomeAdapter.notifyDataSetChanged();
//                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(AnswerActivity.this, "获取列表消息异常", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 初始化首页适配器
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<ArticleAnswerBean>(this, R.layout.item_answer_detail, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, final ArticleAnswerBean ArticleAnswerBean, int position) {
                holder.setText(R.id.tv_answer_briefintro, ArticleAnswerBean.getArticle_detail_content());
                holder.setText(R.id.tv_answer_vote_number, ArticleAnswerBean.getArticle_detail_vote_number() + "点赞");
                holder.setText(R.id.tv_answer_comment_number, ArticleAnswerBean.getArticle_detail_comment_number() + "评论");
                holder.setText(R.id.tv_answer_time, TimeTransferUtils.dateCalculator(ArticleAnswerBean.getArticle_detail_time(), "-"));
                holder.setOnClickListener(R.id.tv_answer_briefintro, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AnswerActivity.this, AnswerDetailActivity.class);
                        intent.putExtra("ArticleAnswerBean", ArticleAnswerBean.getArticle_detail_content());
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
