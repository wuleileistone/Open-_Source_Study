package com.android.xzl.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.xzl.myapplication.R;
import com.android.xzl.myapplication.adapter.BottomSheetDialogAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt1;
    private Button mBt2;
    private int mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);


        mBt1 = (Button) findViewById(R.id.bt1);
        mBt2 = (Button) findViewById(R.id.bt2);

        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mHeight = wm.getDefaultDisplay().getHeight();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:

                List<String> list = new ArrayList<>();
                int i = 0;
                View contentView = View.inflate(this, R.layout.rv_bottomsheetdialog, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(contentView);
                BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) contentView.getParent());
                mDialogBehavior.setPeekHeight(mHeight/2);
                RecyclerView rv = (RecyclerView) contentView.findViewById(R.id.rv);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rv.setLayoutManager(linearLayoutManager);

                while (i < 20) {
                    list.add("这是测试" + i);
                    i++;
                }

                BottomSheetDialogAdapter bottomSheetDialogAdapter = new BottomSheetDialogAdapter(this,list);
                rv.setAdapter(bottomSheetDialogAdapter);

                bottomSheetDialog.show();


                break;
            case R.id.bt2:

                break;
        }
    }


}
