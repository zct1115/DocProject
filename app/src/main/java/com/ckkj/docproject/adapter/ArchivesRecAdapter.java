package com.ckkj.docproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import  com.ckkj.docproject.R;
import  com.ckkj.docproject.bean.ArchivesData;
import  com.ckkj.docproject.bean.ArchivesData.DataBean;


/**
 * Created by XISEVEN on 2017/5/24.
 */

public class ArchivesRecAdapter extends BaseQuickAdapter<ArchivesData.DataBean,BaseViewHolder> {


    public ArchivesRecAdapter() {
        super(R.layout.item_content_archive);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.archiveNum_tv, item.getIdentification())
            .setText(R.id.archiveName_tv, item.getTitle());
    }
}
