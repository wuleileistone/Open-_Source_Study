package com.android.xzl.myapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.android.xzl.myapplication.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

/**
 * Created by wulei on 2020/3/15.
 */

//使用知乎的时候用的Uri,而使用图片选择库的时候选择的是ImageItem
public class ImageAdapter extends BaseQuickAdapter<ImageItem, BaseViewHolder> {
    private Context context;
    public ImageAdapter(Context context, List<ImageItem> data) {
        super(R.layout.image_resouse_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ImageItem s) {
        Glide.with(context).load(s.path).into((ImageView) baseViewHolder.getView(R.id.image_view));
    }
}
