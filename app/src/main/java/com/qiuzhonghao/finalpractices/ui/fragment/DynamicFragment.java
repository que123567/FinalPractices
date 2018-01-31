package com.qiuzhonghao.finalpractices.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.MainHomeBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {

    RecyclerView mRecyclerView;
    Unbinder unbinder;
    CommonAdapter<MainHomeBean> mMainHomeAdapter;
    List<MainHomeBean> mBeanList;

    public DynamicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        View itemView = inflater.inflate(R.layout.item_main_dynamic, container, false);
        unbinder = ButterKnife.bind(this, itemView);
        mRecyclerView = view.findViewById(R.id.rv_dynamic);
        initData();
        initAdapter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 初始化动态数据
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
        mBeanList.add(mainHomeBean);
        mBeanList.add(mainHomeBean);
    }

    /**
     * 初始化动态适配器
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<MainHomeBean>(getActivity(), R.layout.item_main_dynamic, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, MainHomeBean mainHomeBean, int position) {
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }

}
