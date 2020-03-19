package com.android.xzl.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.xzl.myapplication.R;
import com.deadline.statebutton.StateButton;
import com.tamsiree.rxkit.RxPermissionsTool;
import com.tamsiree.rxkit.RxTool;
import com.tamsiree.rxkit.view.RxToast;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class StateButtonActivity extends AppCompatActivity implements View.OnClickListener {

    private StateButton mStateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_button);

        mStateButton = (StateButton) findViewById(R.id.sb);

        mStateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //rxPermission 的权限申请
//        RxPermissionsTool.with(this).addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .addPermission(Manifest.permission.CAMERA).initPermission();
         //无需等待的土司
        RxTool.init(this);
//        RxToast.showToast("这是Rx的土司");
        boolean b = RxToast.doubleClickExit();
        if (b) {
            finish();
        }
    }
}
