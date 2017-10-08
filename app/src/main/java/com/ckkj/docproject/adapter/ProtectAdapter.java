package com.ckkj.docproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.docproject.R;
import com.ckkj.docproject.bean.cupboard.ProtectData;

/**
 * Created by zct11 on 2017/8/9.
 */

public class ProtectAdapter extends BaseQuickAdapter<ProtectData.DataBean,BaseViewHolder> {

    public ProtectAdapter() {
        super(R.layout.item_protect);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProtectData.DataBean item) {
      helper.setText(R.id.pro_storeroomCode,item.getStoreroomCode()).setText(R.id.pro_protectType,item.getProtectType())
      .setText(R.id.pro_operUser,item.getOperUser());
    }
}
