package com.qiuzhonghao.finalpractices.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.NoticeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {

    Unbinder unbinder;
    RecyclerView mRecyclerView;
    CommonAdapter<NoticeBean> mMainHomeAdapter;
    List<NoticeBean> mBeanList;

    public NoticeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView = view.findViewById(R.id.rv_notice);
        initData();
        initAdapter();
        return view;
    }

    /**
     *
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<NoticeBean>(getActivity(), R.layout.item_main_notice, mBeanList) {

            @Override
            protected void convert(ViewHolder holder, NoticeBean noticeBean, int position) {
//                holder.setText(R.id.tv_notice_author, noticeBean.getNotice_author());
//                holder.setText(R.id.tv_notice_time, noticeBean.getNotice_time());
//                holder.setText(R.id.tv_notice_title, noticeBean.getNotice_title());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }

    /**
     *
     */
    private void initData() {
        mBeanList = new ArrayList<>();
        NoticeBean noticeBean = new NoticeBean();
        mBeanList.add(noticeBean);
        mBeanList.add(noticeBean);
        mBeanList.add(noticeBean);
        mBeanList.add(noticeBean);
        getNoticeData();
    }

    private void getNoticeData() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
