package com.example.mvplrider.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseAdapter;
import com.example.mvplrider.model.tongpaodata.TopicBean;

import java.util.List;

import retrofit2.http.POST;

/**
 *
 */
public class DiscussedAdapter extends BaseAdapter {

    public DiscussedAdapter(List mList, Context context) {
        super(mList, context);
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        TopicBean.DataBean dataBean = (TopicBean.DataBean) data;
        ImageView imgIcon = (ImageView) vh.getViewById(R.id.img_icon);
        TextView txtWord = (TextView) vh.getViewById(R.id.txt_word);
        TextView tvTag = (TextView) vh.getViewById(R.id.tv_tag);
        TextView tvHuman = (TextView) vh.getViewById(R.id.tv_human);
        ConstraintLayout clHome = (ConstraintLayout) vh.getViewById(R.id.cl_home);

        //加载数据2
        RequestOptions requestOptions=new RequestOptions()
                .transform(new RoundedCorners(30));
        Glide.with(context).load(dataBean.getImageUrl()).apply(requestOptions).into(imgIcon);
        txtWord.setVisibility(View.VISIBLE);
        if(position>1){
            txtWord.setText("活动");
            txtWord.setVisibility(View.GONE);
        }

        if(position%3==0){
            clHome.setBackgroundColor(Color.parseColor("#F7F7EE"));
        }else if(position%3==1){
            clHome.setBackgroundColor(Color.parseColor("#F9EFEE"));
        }else{
            clHome.setBackgroundColor(Color.parseColor("#F1F1FA"));
        }

        tvTag.setText("#"+dataBean.getName()+"#");
        tvHuman.setText(dataBean.getAttentionNum()+"人参与");
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_recommend_topic;
    }
}
