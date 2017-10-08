package com.ckkj.docproject.adapter;

import android.view.ViewGroup;

import  com.ckkj.docproject.bean.DownloadItem;
import zlc.season.practicalrecyclerview.AbstractAdapter;


/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/28
 * Time: 10:02
 * FIXME
 */
public class DownloadAdapter extends AbstractAdapter<DownloadItem, DownloadViewHolder> {
    @Override
    protected DownloadViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new DownloadViewHolder(parent, this);
    }

    @Override
    protected void onNewBindViewHolder(DownloadViewHolder holder, int position) {
        holder.setData(get(position));
    }
}
