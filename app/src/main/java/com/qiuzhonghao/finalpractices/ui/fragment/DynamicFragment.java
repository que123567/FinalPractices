package com.qiuzhonghao.finalpractices.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

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
public class DynamicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    Unbinder unbinder;
    CommonAdapter<DynamicBean> mMainHomeAdapter;
    List<DynamicBean> mBeanList;
    LoadMoreWrapper mLoadMoreWrapper;
    private SwipeRefreshLayout mSwipeLayout;

    public DynamicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        View itemView = inflater.inflate(R.layout.item_main_dynamic, container, false);
        unbinder = ButterKnife.bind(this, itemView);
        mRecyclerView = view.findViewById(R.id.rv_dynamic);
        mSwipeLayout = view.findViewById(R.id.swipe_dynamic);
        initSwipeRefreshLayout(mSwipeLayout);//下拉刷新
        initAdapter();//适配器
        getData();//数据源
        return view;
    }

    /**
     * 下拉刷新
     *
     * @param swipeLayout
     */
    private void initSwipeRefreshLayout(SwipeRefreshLayout swipeLayout) {
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        mSwipeLayout.setDistanceToTriggerSync(200);
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeLayout.setOnRefreshListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取信息
     */
    private void getData() {
        getDynamicInfo();
    }


    /**
     * 初始化动态适配器
     */
    private void initAdapter() {
        mBeanList = new ArrayList<>();
        mMainHomeAdapter = new CommonAdapter<DynamicBean>(getActivity(), R.layout.item_main_dynamic, mBeanList) {

            @Override
            protected void convert(ViewHolder holder, DynamicBean dynamicBean, int position) {
                holder.setText(R.id.tv_dynamic_author, dynamicBean.getDynamic_author());
                holder.setText(R.id.tv_dynamic_briefintro, dynamicBean.getDynamic_content());
                holder.setText(R.id.tv_dynamic_comment_number, dynamicBean.getDynamic_comment_number());
                holder.setText(R.id.tv_dynamic_vote_number, dynamicBean.getDynamic_vote_number());
            }
        };
        mLoadMoreWrapper = new LoadMoreWrapper(mMainHomeAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                getData();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mLoadMoreWrapper);
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
                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "获取动态信息异常", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRefresh() {
        getDynamicInfo();
        mSwipeLayout.setRefreshing(false);
    }
}