package com.ckkj.docproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ckkj.docproject.R;
import com.ckkj.docproject.bean.cupboard.HumitureData;

/**
 * Created by zct11 on 2017/8/9.
 */

public class HumitureAdapter extends BaseQuickAdapter<HumitureData.DataBean,BaseViewHolder> {

    public HumitureAdapter() {
        super(R.layout.item_humiture);
    }

    @Override
    protected void convert(BaseViewHolder helper, HumitureData.DataBean item) {
        helper.setText(R.id.hum_data, item.getCheckData()).setText(R.id.hum_takeSteps, item.getCustodian())
        ;
    }
}
