package com.android.xzl.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.xzl.myapplication.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by wulei on 2020/3/16.
 */
public class BottomSheetDialogAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    public BottomSheetDialogAdapter(Context context,List<String> data) {
        super(R.layout.item_tv_sheetdialog, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_content, s);


        baseViewHolder.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了当前的" + baseViewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
