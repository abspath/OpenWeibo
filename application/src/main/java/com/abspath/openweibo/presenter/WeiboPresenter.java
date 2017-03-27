package com.abspath.openweibo.presenter;

import com.abspath.openweibo.AppManager;
import com.abspath.openweibo.data.model.Weibo;
import com.abspath.openweibo.interfaze.PreCallback;
import com.abspath.openweibo.interfaze.UpdateType;
import com.abspath.openweibo.interfaze.WeiboContract;
import com.abspath.openweibo.util.Apps;
import com.abspath.openweibo.util.RxUtils;
import com.github.huajianjiang.net.rxjava.MySubscriber;

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
    public void start() {
        loadWeibos(false, UpdateType.TYPE_NONE);
    }

    @Override
    public void stop() {
    }

    @Override
    public void loadWeibos(boolean forceUpdate, UpdateType updateType) {
        if (!isActive() || !Apps.checkLoginStatus()) return;
        RxUtils.applyBase(
                AppManager.getInstance().getWeiboService().getWeiboList(Apps.getAccessToken()))
                .subscribe(new MySubscriber<>(new PreCallback<Weibo>(view) {
                    @Override
                    public void onSuccess(Weibo result) {
                        view.showWeiboList(result);
                    }
                }));
    }
}
