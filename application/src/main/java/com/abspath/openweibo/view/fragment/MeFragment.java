package com.abspath.openweibo.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abspath.openweibo.R;
import com.abspath.openweibo.interfaze.BaseContract;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class MeFragment extends BaseFragment {
    private static final String TAG = MeFragment.class.getSimpleName();

    @Override
    public BaseContract.BaseIPresenter<?> getPresenter() {
        return null;
    }

    @Override
    public void onInitView(View root) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_me, container, false);
    }
}
