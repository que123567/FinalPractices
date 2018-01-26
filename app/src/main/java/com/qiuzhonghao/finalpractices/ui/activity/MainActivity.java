package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.bean.MainHomeBean;
import com.qiuzhonghao.finalpractices.ui.fragment.MainHomeFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    RecyclerView mRecyclerView;
    CommonAdapter<MainHomeBean> mMainHomeAdapter;
    List<MainHomeBean> mBeanList;


    @BindView(R.id.main_viewpager)
    ViewPager sMainViewPager;

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
        initMainViewPager();
    }


    private void initMainViewPager() {
        mFragmentList.add(new MainHomeFragment());

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
        changeSelectTabState(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void changeSelectTabState(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
