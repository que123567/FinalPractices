package com.qiuzhonghao.finalpractices.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.DynamicBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.DynamicService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {

    RecyclerView mRecyclerView;
    Unbinder unbinder;
    CommonAdapter<DynamicBean> mMainHomeAdapter;
    List<DynamicBean> mBeanList;

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
        getDynamicInfo();
    }


    /**
     * 初始化动态适配器
     */
    private void initAdapter() {
        mMainHomeAdapter = new CommonAdapter<DynamicBean>(getActivity(), R.layout.item_main_dynamic, mBeanList) {

            @Override
            protected void convert(ViewHolder holder, DynamicBean dynamicBean, int position) {
                holder.setText(R.id.tv_dynamic_author, dynamicBean.getDynamic_author());
                holder.setText(R.id.tv_dynamic_briefintro, dynamicBean.getDynamic_content());
                holder.setText(R.id.tv_dynamic_comment_number, dynamicBean.getDynamic_comment_number());
                holder.setText(R.id.tv_dynamic_vote_number, dynamicBean.getDynamic_vote_number());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMainHomeAdapter);
    }

    /**
     * 获取动态信息
     */
    private void getDynamicInfo() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.DYNAMIC);
        DynamicService dynamicService = retrofit.create(DynamicService.class);
        dynamicService.getDynamicInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DynamicBean>>() {
                    @Override
                    public void accept(List<DynamicBean> dynamicBeans) throws Exception {
                        mBeanList.addAll(dynamicBeans);
                        mMainHomeAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "获取动态信息异常", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
