package com.abspath.openweibo.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abspath.openweibo.R;
import com.abspath.openweibo.adapter.WeiboAdapter;
import com.abspath.openweibo.data.model.Weibo;
import com.abspath.openweibo.interfaze.BaseContract;
import com.abspath.openweibo.interfaze.WeiboContract;
import com.abspath.openweibo.util.Logger;
import com.abspath.openweibo.util.ViewUtils;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class WeiboFragment extends BaseFragment implements WeiboContract.IView {
    private static final String TAG = WeiboFragment.class.getSimpleName();
    private WeiboContract.IPresenter mPresenter;
    private RecyclerView mRv;
    private WeiboAdapter mAdapter;

    @Override
    public BaseContract.BaseIPresenter<?> getPresenter() {
        return mPresenter;
    }

    @Override
    public void onInitView(View root) {
        mRv = ViewUtils.find(root, R.id.rv);
        mAdapter = new WeiboAdapter(getActivity());
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public View onBuildView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_home, container, false);
    }

    @Override
    public void bindPresenter(WeiboContract.IPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showWeiboList(Weibo weibo) {
        Logger.e(TAG,weibo.statuses.toString());
        mAdapter.insertItems(weibo.statuses);
    }
}
