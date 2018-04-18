package com.qiuzhonghao.finalpractices.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.MainHomeArticleBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.ArticleService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.ui.activity.AddQuestionActivity;
import com.qiuzhonghao.finalpractices.ui.activity.AnswerActivity;
import com.qiuzhonghao.finalpractices.ui.activity.AuthorDetailActivity;
import com.qiuzhonghao.finalpractices.ui.custom.SearchEditText;
import com.qiuzhonghao.finalpractices.util.TimeTransferUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_main_home_head)
    CircleImageView mIvHead;
    @BindView(R.id.tv_main_home_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_main_home_time)
    TextView mTvTime;
    @BindView(R.id.tv_main_home_title)
    TextView mTvTitle;
    @BindView(R.id.tv_main_home_briefintro)
    TextView mTvBriefintro;
    @BindView(R.id.tv_main_home_vote_number)
    TextView mTVoteNumber;
    @BindView(R.id.tv_main_home_comment_number)
    TextView mTvCommentNumber;

    SearchEditText mSearchEditText;
    RecyclerView mRecyclerView;
    CommonAdapter<MainHomeArticleBean> mMainHomeAdapter;
    List<MainHomeArticleBean> mBeanList;
    Unbinder unbinder;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public MainHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        View itemView = inflater.inflate(R.layout.item_main_home, container, false);
        unbinder = ButterKnife.bind(this, itemView);
        mRecyclerView = view.findViewById(R.id.rv_main_home);
        mSearchEditText = view.findViewById(R.id.et_main_search);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_main_home);
        initSwipeRefreshLayout(mSwipeRefreshLayout);
        setOnEditorActionListener(mSearchEditText);

        initAdapter();
        initData();
        return view;
    }

    /**
     * 初始化首页数据
     */
    private void initData() {
        getMainHomeInfo();
    }


    /**
     * 初始化首页适配器
     */
    private void initAdapter() {
        mBeanList = new ArrayList<>();
        mMainHomeAdapter = new CommonAdapter<MainHomeArticleBean>(getActivity(), R.layout.item_main_home, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, MainHomeArticleBean mainHomeBean, int position) {
                holder.setText(R.id.tv_main_home_author, mainHomeBean.getArticle_author());
                holder.setText(R.id.tv_main_home_time, TimeTransferUtils.dateCalculator(mainHomeBean.getArticle_time(), "-"));
                holder.setText(R.id.tv_main_home_title, mainHomeBean.getArticle_name());
                holder.setText(R.id.tv_main_home_briefintro, mainHomeBean.getArticle_content());
                holder.setText(R.id.tv_main_home_vote_number, mainHomeBean.getArticle_vote_number());
                holder.setText(R.id.tv_main_home_comment_number, mainHomeBean.getArticle_comment_number());
                initsetOnClick(holder, position);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }

    /**
     * 对item View内的布局项监听
     *
     * @param holder
     */
    private void initsetOnClick(final ViewHolder holder, final int position) {
        holder.setOnClickListener(R.id.iv_main_home_head, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AuthorDetailActivity.class);
                TextView tv = holder.getView(R.id.tv_main_home_author);
                intent.putExtra("NICKNAME", tv.getText().toString());
                startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.tv_main_home_briefintro, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateBeanInfo(position);
            }
        });
        holder.setOnClickListener(R.id.tv_main_home_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateBeanInfo(position);
            }
        });

    }

    /**
     * 主页数据传递至问题回答界面
     *
     * @param position
     */
    private void translateBeanInfo(int position) {
        checkNotNull(mBeanList);//guava 判空
        Intent intent = new Intent(getActivity(), AnswerActivity.class);
        intent.putExtra("MainHomeBean", mBeanList.get(position));
        startActivity(intent);
    }

    /**
     * EditText回车键监听
     *
     * @param editText
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setOnEditorActionListener(EditText editText) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    if (v != null) {
                        getSearchInfo(v.getText().toString().trim());//搜索
                    }
                    return true;
                }
                return false;
            }
        });
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int width = v.getWidth();
                int paddingRight = v.getPaddingRight();

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    boolean touchable = event.getX() > (width - paddingRight - 50)
                            && (event.getX() < ((width - paddingRight)));
                    if (touchable) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), AddQuestionActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });

    }

    /**
     * 搜索
     */
    private void getSearchInfo(String str) {
        mBeanList.clear();
        Retrofit retrofit = RxService.getRetrofitInstance(API.ARTICLE);
        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.getSearchInfo(str).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MainHomeArticleBean>>() {
                    @Override
                    public void accept(List<MainHomeArticleBean> mainHomeArticleBeans) throws Exception {
                        mBeanList.addAll(mainHomeArticleBeans);
                        mMainHomeAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "搜索成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    /**
     * 获取主页数据
     */
    private void getMainHomeInfo() {
        mBeanList.clear();
        Retrofit retrofit = RxService.getRetrofitInstance(API.ARTICLE);

        ArticleService articleService = retrofit.create(ArticleService.class);
        articleService.getArticleInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MainHomeArticleBean>>() {
                    @Override
                    public void accept(List<MainHomeArticleBean> mainHomeArticleBeans) throws Exception {
                        mBeanList.addAll(mainHomeArticleBeans);
                        mMainHomeAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "异常", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    /**
     * 下拉刷新
     *
     * @param swipeRefreshLayout
     */
    private void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        getMainHomeInfo();
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
