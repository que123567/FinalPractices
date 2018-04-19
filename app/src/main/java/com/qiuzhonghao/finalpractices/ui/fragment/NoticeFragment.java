package com.qiuzhonghao.finalpractices.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.bean.NotifyBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.NotifyService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.util.TimeTransferUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {

    Unbinder unbinder;
    RecyclerView mRecyclerView;
    CommonAdapter<NotifyBean> mMainHomeAdapter;
    List<NotifyBean> mBeanList;
    String author;

    public NoticeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView = view.findViewById(R.id.rv_notice);
        initData();
        return view;
    }

    /**
     *
     */
    private void initAdapter(List<NotifyBean> mBeanList) {

        mMainHomeAdapter = new CommonAdapter<NotifyBean>(getActivity(), R.layout.item_main_notice, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, NotifyBean notifyBean, int position) {
                CircleImageView circleImageView = holder.getView(R.id.iv_dynamic_head);
                final String HeadUrl = API.BASE_URL + "final/file/tmpFile/" + notifyBean.getNotify_from() + ".png";
                Glide.with(getActivity()).load(HeadUrl).into(circleImageView);
                holder.setText(R.id.tv_notice_author, notifyBean.getNotify_from());
                holder.setText(R.id.tv_notice_title, notifyBean.getNotify_title());
                holder.setText(R.id.tv_notice_time, TimeTransferUtils.dateCalculator(notifyBean.getNotify_time(), "-"));
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMainHomeAdapter);
        mMainHomeAdapter.notifyDataSetChanged();
    }

    /**
     *
     */
    private void initData() {
        mBeanList = new ArrayList<>();
        getNoticeData();
    }

    private void getNoticeData() {
        Retrofit retrofit = RxService.getRetrofitInstance(API.NOTICE);
        NotifyService notifyService = retrofit.create(NotifyService.class);
        SharedPreferences share = getActivity().getSharedPreferences("TOKEN", MODE_PRIVATE);
        author = share.getString("UserNickName", "null");
        notifyService.getNotify("赵小刚")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NotifyBean>>() {
                    @Override
                    public void accept(List<NotifyBean> notifyBean) throws Exception {
                        mBeanList.addAll(notifyBean);
                        initAdapter(mBeanList);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
