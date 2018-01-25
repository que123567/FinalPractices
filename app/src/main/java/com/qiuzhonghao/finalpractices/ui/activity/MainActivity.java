package com.qiuzhonghao.finalpractices.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.ui.fragment.MainHomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
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
        mFragmentList.add(new MainHomeFragment());
        mFragmentList.add(new MainHomeFragment());
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
