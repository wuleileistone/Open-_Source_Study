package com.android.xzl.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.xzl.myapplication.R;

import java.util.List;

import cn.qqtheme.framework.picker.SinglePicker;

public class WheelPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_picker);
    }


    public void onSinglePicker(List<String> list) {
        WindowManager windowManager = getWindowManager();
        int width = windowManager.getDefaultDisplay().getWidth();
        SinglePicker<String> picker = new SinglePicker<String>(this, list);
        picker.setCanceledOnTouchOutside(false);
        //当前选择的位置
        picker.setSelectedIndex(0);
        picker.setCycleDisable(true);
        //弹出的位置默认是底部弹出的
        picker.setGravity( Gravity.CENTER);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                Toast.makeText(WheelPickerActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });
        picker.show();
    }


}
