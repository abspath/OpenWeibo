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
import com.abspath.openweibo.interfaze.NetIView;
import com.abspath.openweibo.util.Logger;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */

public abstract class BaseFragment extends Fragment implements NetIView, View.OnClickListener {
    private static final String TAG = BaseFragment.class.getSimpleName();

    private BaseContract.BaseIPresenter<?> mPresenter;

    /**
     * 是否第一次页面加载(也就是是否第一次进行网络数据请求)
     */
    private boolean mFirstVisible = true;

    public abstract BaseContract.BaseIPresenter<?> getPresenter();

    public abstract void onInitView(View root);

    private View mLoading;
    private View mDataErrorWhole;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
    }

    /**
     * 子类可以调用基类来插入一些共性操作界面, eg: loading dialog, data loading error, net error view 等等
     * <p>
     *     <b>注意: 子类传递的 <code>container</code> 必须为一个 <code>FrameLayout</code> 根布局容器</b>
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 一个插入全局操作的界面的子类返回的内容 view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        if (container != null && !(container instanceof FrameLayout))
            throw new IllegalArgumentException(
                    "you should pass a container which is a FrameLayout");
        View result = null;
        if (container != null) {
            result = inflater.inflate(R.layout.fragment_base, container, true);
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
        if ((mFirstVisible) && mPresenter != null && getUserVisibleHint()) {
            Logger.e(TAG, "****onActivityCreated*** loading Data");
            mPresenter.onStart();
            mFirstVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if ((mFirstVisible && mPresenter != null && getUserVisibleHint() &&
             isVisible()))
        {
            Logger.e(TAG, "setUserVisibleHint===>" + "loading Data");
            mPresenter.onStart();
            mFirstVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消网络请求或者异步任务
        if (mPresenter != null) {
            mPresenter.onStop();
            mPresenter.onUnbindView();
        }
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
                retry();
                break;
        }
    }

    private void retry() {
        showLoading();
        //重新加载数据
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    public void showLoadingUI() {
        showLoading();
    }

    @Override
    public void showSuccessUI() {

    }

    @Override
    public void showFailureUI() {
        showDataError();
    }

    @Override
    public void clearUI() {
        hideLoading();
        hideDataError();
    }
}
