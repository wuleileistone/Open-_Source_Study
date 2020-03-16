package com.android.xzl.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.xzl.myapplication.R;

public class QrScanActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);


        mButton1 = (Button) findViewById(R.id.bt_scan);
        mButton2 = (Button) findViewById(R.id.bt_b_scan);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_scan:
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_QRCODE_PERMISSIONS);
                startActivityForResult(new Intent(this, QrScanTestActivity.class),100);
                break;

            case R.id.bt_b_scan:

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String result = data.getStringExtra("jieguo");
            if (resultCode == 111) {// 因为有2个按钮,所以要区分是触发了那个按钮的单击事件,然后把返回的数据放到对应的EditText中
                Toast.makeText(this, "当前获取的值=="+result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
