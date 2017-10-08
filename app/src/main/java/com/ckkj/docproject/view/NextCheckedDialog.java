package com.ckkj.docproject.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ckkj.docproject.R;
import com.ckkj.docproject.bean.examine.Entitive;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ting on 2017/9/14.
 */

public class NextCheckedDialog extends Dialog {

    @BindView(R.id.e_preTaskName)
    TextView ePreTaskName;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.e_replicable)
    CheckBox eReplicable;
    @BindView(R.id.e_accessTo)
    CheckBox eAccessTo;
    @BindView(R.id.e_rejectReason)
    EditText eRejectReason;
    @BindView(R.id.yes)
    Button yes;
    @BindView(R.id.no)
    Button no;

    private Context mcontext;
    private Entitive.DataBean mdatabean;

    public NextCheckedDialog(@NonNull Context context,Entitive.DataBean databean) {
        super(context);
        mcontext=context;
        mdatabean=databean;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置不显示对话框标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置对话框显示哪个布局文件
        setContentView(R.layout.nextchecked_layout);
        init();
    }

    private void init() {
        ePreTaskName.setText(mdatabean.getPreTaskName());

    }

    @OnClick({R.id.yes, R.id.no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yes:
                Toast.makeText(mcontext, "sure", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.no:
                dismiss();
                break;
        }
    }
}
