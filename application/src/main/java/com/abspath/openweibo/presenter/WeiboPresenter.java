package com.abspath.openweibo.presenter;

import com.abspath.openweibo.AppManager;
import com.abspath.openweibo.data.model.Weibo;
import com.abspath.openweibo.interfaze.UpdateType;
import com.abspath.openweibo.interfaze.WeiboContract;
import com.abspath.openweibo.util.RxUtils;
import com.github.huajianjiang.net.Exp;
import com.github.huajianjiang.net.NetCallback;
import com.github.huajianjiang.net.rxjava.MySubscriber;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class WeiboPresenter extends BasePresenter<WeiboContract.IView>
        implements WeiboContract.IPresenter
{
    public WeiboPresenter() {

    }

    @Override
    public void onStart() {
        loadMovies(false, UpdateType.TYPE_NONE);
    }

    @Override
    public void onStop() {

    }

    @Override
    protected void loadDataFromRemote() {

    }

    @Override
    public void loadMovies(boolean forceUpdate, UpdateType updateType) {
        if (view == null || !view.isActive()) return;
        AppManager appManager = AppManager.getInstance(view.getCtxt());
        Oauth2AccessToken accessToken = appManager.getAccessToken();
        if (accessToken == null || !accessToken.isSessionValid()) return;

        RxUtils.applyBase(appManager.getWeiboService().getWeiboList(accessToken.getToken()))
                .subscribe(new MySubscriber<>(new NetCallback<Weibo>() {
                    @Override
                    public void onBefore() {
                        view.showLoadingUI();
                    }

                    @Override
                    public void onSuccess(Weibo result) {
                        view.showWeiboList(result);
                    }

                    @Override
                    public void onFailure(Exp exp) {
                        view.showFailureUI();
                    }

                    @Override
                    public void onEnd() {
                        view.clearUI();
                    }
                }));
    }
}