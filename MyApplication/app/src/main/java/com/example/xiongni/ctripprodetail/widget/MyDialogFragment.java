package com.example.xiongni.ctripprodetail.widget;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.xiongni.ctripprodetail.R;

/**
 * Created by xiongni on 2017/10/9.
 */

public class MyDialogFragment extends DialogFragment{
    private DialogInterface dialogInterface;
    private View customView;

    public DialogInterface getDialogInterface() {
        return dialogInterface;
    }

    public void initDialogFragment(View customView,DialogInterface dialogInterface) {
        this.customView = customView;
        this.dialogInterface = dialogInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让dialogfragment全屏显示
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.TextAppearance_Theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.root_dialog_layout,container);
        //设置dialog
        dialogSetting();
        //初始化自定义view
        initDialog(view);
        return view;
    }

    private void dialogSetting() {
        //设置dialog背景颜色
        Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void initDialog(View view) {
        View cancelBtn = view.findViewById(R.id.cancel_dialog_btn);
        if(dialogInterface != null){// && customView != null
            LinearLayout dialog_container = (LinearLayout) view.findViewById(R.id.dialog_container);
            //将自定义布局添加到根布局中
            //dialog_container.addView(customView);
            //渲染dialog内容
            dialogInterface.initDialog(view);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogInterface.cancelDialog();
                }
            });
        }
    }

    public interface DialogInterface{
        void initDialog(View view);
        void cancelDialog();
    }
}
