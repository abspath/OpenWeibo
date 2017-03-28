package com.abspath.openweibo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.abspath.openweibo.R;
import com.abspath.openweibo.interfaze.BaseContract;
import com.abspath.openweibo.interfaze.PreIView;
import com.abspath.openweibo.util.Logger;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public abstract class BaseFragment extends Fragment implements PreIView, View.OnClickListener {
    private static final String TAG = BaseFragment.class.getSimpleName();

    private BaseContract.BaseIPresenter<?> mP;

    /**
     * 是否第一次页面加载(也就是是否第一次进行网络数据请求)
     */
    private boolean mFirstVisible = true;

    public abstract BaseContract.BaseIPresenter<?> getPresenter();

    private View mLoading;
    private View mDataErrorWhole;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mP = getPresenter();
    }

    public View onBuildView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {return null;}

    public void onInitView(View root){}

    /**
     * 插入一些全局 UI 界面, eg: loading dialog, data loading error, net error view 等等
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 一个包含插入了全局UI界面的内容视图层级 View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View content = onBuildView(inflater, container, savedInstanceState);
        FrameLayout result = null;
        if (content != null) {
            result = (FrameLayout) inflater.inflate(R.layout.fragment_base, container, false);
            result.addView(content, 0);
            mLoading = result.findViewById(R.id.loading_layout);
            mDataErrorWhole = result.findViewById(R.id.data_error_whole_layout);
            mLoading.setOnClickListener(this);
            mDataErrorWhole.setOnClickListener(this);
        }
        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) onInitView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e(TAG, "onActivityCreated==>");
        if (mFirstVisible && mP != null && getUserVisibleHint())
        {
            Logger.e(TAG, "****onActivityCreated*** loading Data");
            mP.start();
            mFirstVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.e(TAG, "setUserVisibleHint==>" + getUserVisibleHint());
        if (mFirstVisible && mP != null && getUserVisibleHint() && isVisible())
        {
            Logger.e(TAG, "setUserVisibleHint===>" + "loading Data");
            mP.start();
            mFirstVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消网络请求或者异步任务
        if (mP != null) {
            mP.stop();
            mP.unbindView();
        }
    }

    protected boolean isFirstVisible() {
        return mFirstVisible;
    }

    private void hideDataError() {
        if (mDataErrorWhole != null && mDataErrorWhole.getVisibility() == View.VISIBLE) {
            mDataErrorWhole.setVisibility(View.GONE);
        }
    }

    private void hideLoading() {
        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            mLoading.setVisibility(View.GONE);
        }
    }

    protected void showLoading() {
        hideDataError();
        if (mLoading != null && mLoading.getVisibility() == View.GONE) {
            mLoading.setVisibility(View.VISIBLE);
        }
    }

    protected void showDataError() {
        hideLoading();
        if (mDataErrorWhole != null && mDataErrorWhole.getVisibility() == View.GONE) {
            mDataErrorWhole.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_error_whole_layout:
                onRetry();
                break;
        }
    }

    protected void onRetry() {
        //重新加载数据
        showLoading();
    }

    @Override
    public void showLoadingUi() {
        showLoading();
    }

    @Override
    public void showSuccessUi() {
    }

    @Override
    public void showFailureUi() {
        showDataError();
    }

    @Override
    public void clearPreUi() {
        hideLoading();
        hideDataError();
    }
}
