package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.ui.fragment.DynamicFragment;
import com.qiuzhonghao.finalpractices.ui.fragment.MainHomeFragment;
import com.qiuzhonghao.finalpractices.ui.fragment.NoticeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.main_viewpager)
    ViewPager sMainViewPager;
    @BindView(R.id.iv_home_page)
    ImageView mIvHomePage;
    @BindView(R.id.tv_home_page)
    TextView mTvHomePage;
    @BindView(R.id.iv_main_dynamic)
    ImageView mIvMainDynamic;
    @BindView(R.id.tv_main_dynamic)
    TextView mTvMainDynamic;
    @BindView(R.id.iv_main_message)
    ImageView mIvMainMessage;
    @BindView(R.id.tv_main_message)
    TextView mTvMainMessage;
    @BindView(R.id.iv_main_more)
    ImageView mIvMainMore;
    @BindView(R.id.tv_main_more)
    TextView mTvMainMore;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override

    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.main_main_home_blue).into(mIvHomePage);
        mTvHomePage.setTextColor(getResources().getColor(R.color.lightblue));
        setSupportActionBar(mToolbar);//TODO 自定义edittext
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        initMainViewPager();
        initSelectTabState();
    }


    private void initMainViewPager() {
        mFragmentList.add(new MainHomeFragment());
        mFragmentList.add(new DynamicFragment());
        mFragmentList.add(new NoticeFragment());
        mFragmentList.add(new DynamicFragment());

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };
        sMainViewPager.setAdapter(fragmentPagerAdapter);
        sMainViewPager.setOffscreenPageLimit(4);
        sMainViewPager.addOnPageChangeListener(this);
    }

    /**
     * OnPageChangeListener
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initSelectTabState();
        changeSelectTabState(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 底栏颜色置灰(初始化)
     */
    private void initSelectTabState() {
        Glide.with(this).load(R.drawable.main_main_home_gray).into(mIvHomePage);
        Glide.with(this).load(R.drawable.main_dynamic_gray).into(mIvMainDynamic);
        Glide.with(this).load(R.drawable.main_message_gray).into(mIvMainMessage);
        Glide.with(this).load(R.drawable.main_more_gray).into(mIvMainMore);
        mTvHomePage.setTextColor(getResources().getColor(R.color.lightgray));
        mTvMainDynamic.setTextColor(getResources().getColor(R.color.lightgray));
        mTvMainMessage.setTextColor(getResources().getColor(R.color.lightgray));
        mTvMainMore.setTextColor(getResources().getColor(R.color.lightgray));
    }

    /**
     * 底栏颜色处理
     *
     * @param position
     */
    private void changeSelectTabState(int position) {
        switch (position) {
            case 0:
                Glide.with(this).load(R.drawable.main_main_home_blue).into(mIvHomePage);
                mTvHomePage.setTextColor(getResources().getColor(R.color.lightblue));
                break;
            case 1:
                Glide.with(this).load(R.drawable.main_dynamic_blue).into(mIvMainDynamic);
                mTvMainDynamic.setTextColor(getResources().getColor(R.color.lightblue));
                break;
            case 2:
                Glide.with(this).load(R.drawable.main_message_blue).into(mIvMainMessage);
                mTvMainMessage.setTextColor(getResources().getColor(R.color.lightblue));
                break;
            case 3:
                Glide.with(this).load(R.drawable.main_more_blue).into(mIvMainMore);
                mTvMainMore.setTextColor(getResources().getColor(R.color.lightblue));
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.rl_main_mainhome, R.id.rl_main_dynamics, R.id.rl_main_message, R.id.rl_main_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_main_mainhome:
                sMainViewPager.setCurrentItem(0, false);
                break;
            case R.id.rl_main_dynamics:
                sMainViewPager.setCurrentItem(1, false);
                break;
            case R.id.rl_main_message:
                sMainViewPager.setCurrentItem(2, false);
                break;
            case R.id.rl_main_more:
                sMainViewPager.setCurrentItem(3, false);
                break;
        }
    }
}
