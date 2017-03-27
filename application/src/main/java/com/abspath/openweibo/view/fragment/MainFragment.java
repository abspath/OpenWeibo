package com.abspath.openweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.abspath.openweibo.R;
import com.abspath.openweibo.presenter.WeiboPresenter;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class MainFragment extends Fragment
        implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private static final String TAG = MainFragment.class.getSimpleName();
    private static final int TAB_COUNT = 3;
    private ViewPager mViewPager;
    private BottomNavigationView mNaviView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    public void initView(View rootView) {
        mNaviView = (BottomNavigationView) rootView.findViewById(R.id.bottomNaviView);
        mNaviView.setOnNavigationItemSelectedListener(this);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MainPagerAdapter(getFragmentManager()));
        mViewPager.setOffscreenPageLimit(
                TAB_COUNT - 1);// 主页面中的子页面 Fragment 不仅不会 destroy 而且不会 destroy view
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mViewPager.setCurrentItem(item.getOrder(), false);
        return true;
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {
        MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    WeiboFragment v = new WeiboFragment();
                    WeiboPresenter p = new WeiboPresenter();
                    p.bindView(v);
                    v.bindPresenter(p);
                    return v;
                case 1:
                    return new MsgFragment();
                case 2:
                    return new MeFragment();

            }
            throw new RuntimeException("error in switch navi");
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
