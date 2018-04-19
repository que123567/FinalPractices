package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.MainHomeArticleBean;
import com.qiuzhonghao.finalpractices.bean.NotifyBean;
import com.qiuzhonghao.finalpractices.bean.UsersAddBean;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.NotifyService;
import com.qiuzhonghao.finalpractices.network.RxService;
import com.qiuzhonghao.finalpractices.network.UserService;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * 邀请回答界面
 */
public class UsersAddActivity extends BaseActivity {

    @BindView(R.id.rv_users_add)
    RecyclerView mRecyclerView;

    CommonAdapter<UsersAddBean> mCommonAdapter;
    List<UsersAddBean> mBeanList;
    String author;
    MainHomeArticleBean articleBean;//保存问题简介和标题

    @Override
    public int getLayout() {
        return R.layout.activity_users_add;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        articleBean = (MainHomeArticleBean) bundle.get("MainHomeBean");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArticleDetailInfo();
        initAdapter();
    }


    /**
     * 适配器
     */
    private void initAdapter() {
        mCommonAdapter = new CommonAdapter<UsersAddBean>(this, R.layout.item_users_add, mBeanList) {
            @Override
            protected void convert(ViewHolder holder, final UsersAddBean usersAddBean, int position) {
                holder.setText(R.id.tv_user_item_name, usersAddBean.getNickname());
                holder.setText(R.id.tv_user_item_intro, usersAddBean.getBrief_intro());
                holder.setText(R.id.tv_user_item_vote, usersAddBean.getVote_number() + "赞同");
                holder.setText(R.id.tv_user_item_observe, usersAddBean.getFollow_number() + "关注");
                String HeadUrl = API.BASE_URL + "final/file/tmpFile/" + usersAddBean.getNickname() + ".png";
                ImageView imageView;
                imageView = holder.getView(R.id.iv_main_home_head);
                Glide.with(UsersAddActivity.this).load(HeadUrl).into(imageView);
                holder.setOnClickListener(R.id.ll_add_user, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String targetAuthor = usersAddBean.getNickname();
                        doInvite(articleBean.getArticle_name(), author, targetAuthor);//通知
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCommonAdapter);
    }

    private void doInvite(String title, final String author, final String targetAuthor) {
        Retrofit retrofit = RxService.getRetrofitInstance(API.USER);
        NotifyService notifyService = retrofit.create(NotifyService.class);
        notifyService.doNotify(title, author, targetAuthor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NotifyBean>() {
                    @Override
                    public void accept(NotifyBean notifyBean) throws Exception {
                        if (notifyBean.getResult_code().equals("0")) {
                            showToast(" 成功邀请 " + targetAuthor + " 回答问题");
                            UsersAddBean removeBean = new UsersAddBean();
                            for (UsersAddBean addBean : mBeanList) {
                                if (addBean.getNickname().equals(targetAuthor)) {
                                    removeBean = addBean;
                                }
                            }
                            mBeanList.remove(removeBean);
                            mCommonAdapter.notifyDataSetChanged();
                        } else {
                            showToast("邀请失败,重复邀请!");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast("邀请失败,请检查网络");
                    }
                });

    }

    private void getArticleDetailInfo() {
        mBeanList = new ArrayList<>();
        Retrofit retrofit = RxService.getRetrofitInstance(API.USER);
        UserService userService = retrofit.create(UserService.class);
        userService.getUserInfo()//TODO:根据文章id获取信息
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UsersAddBean>>() {
                    @Override
                    public void accept(List<UsersAddBean> usersAddBeanList) throws Exception {
                        mBeanList.addAll(usersAddBeanList);
                        SharedPreferences share = getSharedPreferences("TOKEN", MODE_PRIVATE);
                        author = share.getString("UserNickName", "null");
                        for (UsersAddBean addBean : usersAddBeanList) {
                            if (addBean.getNickname().equals(author)) {
                                mBeanList.remove(addBean);
                                break;
                            }
                        }
                        mCommonAdapter.notifyDataSetChanged();
//                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(UsersAddActivity.this, "获取列表消息异常", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
