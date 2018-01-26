package com.qiuzhonghao.finalpractices.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.MainHomeBean;
import com.qiuzhonghao.finalpractices.ui.activity.AuthorDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    //    @BindView(R.id.tv_main_home_briefintro)
    TextView mTvBriefintro;
    @BindView(R.id.tv_main_home_vote_number)
    TextView mTVoteNumber;
    @BindView(R.id.tv_main_home_comment_number)
    TextView mTvommentNumber;

    RecyclerView mRecyclerView;
    CommonAdapter<MainHomeBean> mMainHomeAdapter;
    List<MainHomeBean> mBeanList;
    Unbinder unbinder;

    public MainHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        View itemView = inflater.inflate(R.layout.item_main_home, container, false);
        unbinder = ButterKnife.bind(this, itemView);
        mRecyclerView = view.findViewById(R.id.rv_main_home);
        mTvBriefintro = itemView.findViewById(R.id.tv_main_home_briefintro);
        initData();
        initAdapter();
        return view;
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
    }


    /**
     * 初始化首页适配器
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<MainHomeBean>(getActivity(), R.layout.item_main_home, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, MainHomeBean mainHomeBean, int position) {
                holder.setText(R.id.tv_main_home_time, "12分钟");
                holder.setOnClickListener(R.id.iv_main_home_head, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), AuthorDetailActivity.class));
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
