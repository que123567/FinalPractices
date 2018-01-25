package com.qiuzhonghao.finalpractices.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.MainHomeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment extends Fragment {

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
    TextView mTvommentNumber;

    RecyclerView mRecyclerView;
    CommonAdapter<MainHomeBean> mMainHomeAdapter;
    List<MainHomeBean> mBeanList;
    Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        initAdapter();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mBeanList = new ArrayList<>();
        mBeanList.add(new MainHomeBean());
        mBeanList.add(new MainHomeBean());
        mBeanList.add(new MainHomeBean());
        mBeanList.add(new MainHomeBean());
    }

    /**
     * 初始化首页数据
     */
    private void initAdapter() {
        mRecyclerView = new RecyclerView(getActivity());
        mMainHomeAdapter = new CommonAdapter<MainHomeBean>(getActivity(), R.layout.item_main_home, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, MainHomeBean mainHomeBean, int position) {

            }
        };
        mRecyclerView.setAdapter(mMainHomeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    @OnClick({R.id.iv_main_home_head, R.id.tv_main_home_title, R.id.tv_main_home_briefintro, R.id.tv_main_home_comment_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_home_head:
                break;
            case R.id.tv_main_home_title:
                break;
            case R.id.tv_main_home_briefintro:
                break;
            case R.id.tv_main_home_comment_number:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
