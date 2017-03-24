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
 * <br>Date: 2017/3/24
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class SignInFragment extends BaseFragment {

    @Override
    public BaseContract.BaseIPresenter<?> getPresenter() {
        return null;
    }

    @Override
    public View onBuildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.acti_sign_in, container, false);
    }
}
