package com.abspath.openweibo.presenter;

import com.abspath.openweibo.AppManager;
import com.abspath.openweibo.data.model.Weibo;
import com.abspath.openweibo.interfaze.PreCallback;
import com.abspath.openweibo.interfaze.UpdateType;
import com.abspath.openweibo.interfaze.WeiboContract;
import com.abspath.openweibo.util.Apps;
import com.abspath.openweibo.util.Ifs;
import com.abspath.openweibo.util.Rxs;
import com.github.huajianjiang.net.Exp;
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
    //请求数据时的第一页页号
    private int mPage = 1;

    @Override
    public void start() {
        loadWeibos(UpdateType.TYPE_INIT);
    }

    @Override
    public void loadWeibos(final UpdateType updateType)
    {
        if (!isActive()) return;
        if (UpdateType.TYPE_REFRESH == updateType) {
            mPage = 1;
        } else if (UpdateType.TYPE_MORE == updateType) {
            mPage++;
        }
        task = Rxs.applyBase(AppManager.getInstance().getWeiboService()
                .getWeibos(Apps.getAccessToken(), mPage, 20))
                .subscribe(new MySubscriber<>(new PreCallback<Weibo>(view) {
                    @Override
                    public void onBefore() {
                        if (UpdateType.TYPE_INIT == updateType) super.onBefore();
                    }

                    @Override
                    public void onSuccess(Weibo result) {
                        if (Ifs.isNullOrEmpty(result.statuses)) {
                            if (updateType == UpdateType.TYPE_MORE) {
                                view.showNoMoreWeiboHint();
                            } else if (updateType == UpdateType.TYPE_REFRESH) {
                                view.showRefreshFailureHint();
                            }
                        } else {
                            view.showWeibos(result, updateType);
                        }
                    }

                    @Override
                    public void onFailure(Exp exp) {
                        if (UpdateType.TYPE_INIT == updateType) super.onFailure(exp);
                        else view.showExceptionHint(exp.getErrorMsg());
                    }
                }));
    }
}
