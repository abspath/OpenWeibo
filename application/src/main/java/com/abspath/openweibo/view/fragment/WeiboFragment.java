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
import com.abspath.openweibo.interfaze.UpdateType;
import com.abspath.openweibo.interfaze.WeiboContract;
import com.abspath.openweibo.util.Logger;
import com.abspath.openweibo.util.Msgs;
import com.abspath.openweibo.util.Views;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class WeiboFragment extends BaseFragment implements WeiboContract.IView {
    private static final String TAG = WeiboFragment.class.getSimpleName();
    private WeiboContract.IPresenter mP;
    private SwipyRefreshLayout mSrl;
    private RecyclerView mRv;
    private WeiboAdapter mAdapter;

    @Override
    public BaseContract.BaseIPresenter<?> getPresenter() {
        return mP;
    }

    @Override
    public void onInitView(View root) {
        mSrl = Views.find(root, R.id.srl);
        mSrl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    mP.loadWeibos(UpdateType.TYPE_REFRESH);
                } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
                    mP.loadWeibos(UpdateType.TYPE_MORE);
                }
            }
        });
        mRv = Views.find(root, R.id.rv);
        mAdapter = new WeiboAdapter(getActivity());
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public View onBuildView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_home, container, false);
    }

    @Override
    public void bindPresenter(WeiboContract.IPresenter presenter) {
        mP = presenter;
    }

    @Override
    public void showWeibos(Weibo weibo, UpdateType updateType) {
        Logger.e(TAG, weibo.statuses.size() + "");
        if (updateType == UpdateType.TYPE_REFRESH || updateType == UpdateType.TYPE_INIT) {
            mAdapter.invalidateItems(weibo.statuses);
        } else if (updateType == UpdateType.TYPE_MORE) {
            mAdapter.insertItems(weibo.statuses);
        }
    }

    @Override
    public void showRefreshFailureHint() {
        Msgs.shortToast(getContext(), R.string.refresh_failure);
    }

    @Override
    public void showNoMoreWeiboHint() {
        Msgs.shortToast(getContext(), R.string.no_more_weibo_data);
    }

    @Override
    public void showExceptionHint(String msg) {
        Msgs.shortToast(getContext(), msg);
    }

    @Override
    protected void onRetry() {
        super.onRetry();
        mP.loadWeibos(UpdateType.TYPE_RETRY);
    }

    @Override
    public void clearPreUi() {
        super.clearPreUi();
        mSrl.setRefreshing(false);
    }
}
