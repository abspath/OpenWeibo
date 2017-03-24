package com.abspath.openweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abspath.openweibo.R;
import com.abspath.openweibo.data.model.User;
import com.abspath.openweibo.data.model.Weibo;
import com.bumptech.glide.Glide;
import com.github.huajianjiang.baserecyclerview.widget.BaseAdapter;
import com.github.huajianjiang.baserecyclerview.widget.BaseViewHolder;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class WeiboAdapter extends BaseAdapter<BaseViewHolder,Weibo.WeiboItem> {

    public WeiboAdapter(Context ctxt) {
        super(ctxt);
    }

    @Override
    public BaseViewHolder onBuildViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(inflater.inflate(R.layout.item_weibo,parent,false)) {
            @Override
            public void onItemClick(RecyclerView rv, View v) {

            }
        };
    }

    @Override
    public void onPopulateViewHolder(BaseViewHolder holder, int position) {
        Weibo.WeiboItem item = getItem(position);
        if (item == null) return;
        User user = item.user;
        if (user != null) {
            Glide.with(ctxt).load(user.profile_image_url).centerCrop()
                    .thumbnail(0.1f).dontAnimate().into((ImageView) holder.getView(R.id.avatar));
            ((TextView) holder.getView(R.id.userName)).setText(user.screen_name);
            ((TextView) holder.getView(R.id.content)).setText(item.text);
        }
    }

}
