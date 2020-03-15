package com.android.xzl.myapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.android.xzl.myapplication.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by wulei on 2020/3/15.
 */
public class ImageAdapter extends BaseQuickAdapter<Uri, BaseViewHolder> {
    private Context context;
    public ImageAdapter(Context context, List<Uri> data) {
        super(R.layout.image_resouse_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Uri s) {
        Glide.with(context).load(s).into((ImageView) baseViewHolder.getView(R.id.image_view));
    }
}
