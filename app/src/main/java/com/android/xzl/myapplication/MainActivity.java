package com.android.xzl.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.xzl.myapplication.adapter.ImageAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageCropActivity;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHOOSE = 1;
    private static final int IMAGE_PICKER = 2;
    private Button mBtImage;
    private List<Uri> mSelected;
    private RecyclerView mRecyclerView;
    private List<Uri> paths;
    private ImageAdapter mImageAdapter;
    private int requestCode = 123;
    private List<ImageItem> mImageItemList;


    //photoSelectr
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paths = new ArrayList<>();
        mImageItemList = new ArrayList<>();
        mBtImage = (Button) findViewById(R.id.bt_image_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mImageAdapter = new ImageAdapter(this, mImageItemList);
        mRecyclerView.setAdapter(mImageAdapter);

        mBtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, requestCode);

                //ImagePicker的使用
                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);

                //PhotoSelector的使用

                //使用知乎图片选择器的方式，记得有回调
//                Matisse.from(MainActivity.this)
//                        .choose(MimeType.ofAll())
//                        .countable(true)
//                        .maxSelectable(9)
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f)
//                        .imageEngine(new GlideEngine())
//                        .showPreview(false) // Default is `true`
//                        .autoHideToolbarOnSingleTap(true)
//                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            paths.clear();
            paths.addAll(mSelected);
           // mImageAdapter.setNewData(paths);
            mImageAdapter.notifyDataSetChanged();
        }
//        if (requestCode == IMAGE_PICKER && resultCode == RESULT_OK) {
//            if (data != null && requestCode == IMAGE_PICKER) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                mImageItemList.clear();
//                mImageItemList.addAll(images);
//                mImageAdapter.setNewData(mImageItemList);
//                mImageAdapter.notifyDataSetChanged();
//            } else {
//                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
//            }
//        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                mImageItemList.clear();
                mImageItemList.addAll(images);
                mImageAdapter.setNewData(mImageItemList);
                mImageAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
