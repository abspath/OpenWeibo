package com.abspath.openweibo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.abspath.openweibo.data.model.PubTimeline;
import com.github.huajianjiang.baserecyclerview.widget.BaseAdapter;
import com.github.huajianjiang.baserecyclerview.widget.BaseViewHolder;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class PubTimelineAdapter extends BaseAdapter<BaseViewHolder,PubTimeline> {

    public PubTimelineAdapter(Context ctxt) {
        super(ctxt);
    }

    @Override
    public BaseViewHolder onBuildViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onPopulateViewHolder(BaseViewHolder holder, int position) {

    }


}
