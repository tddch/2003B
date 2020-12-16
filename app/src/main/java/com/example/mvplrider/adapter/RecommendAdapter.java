package com.example.mvplrider.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseAdapter;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.homeGoodsBean;
import com.example.mvplrider.ui.tongpao.activity.BigImageActivity;
import com.example.mvplrider.utils.ClassUtils;
import com.example.mvplrider.utils.DateUtils;
import com.example.mvplrider.utils.ImageLoader;
import com.example.mvplrider.utils.TxtUtils;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

/**
 *
 */
public class RecommendAdapter extends BaseAdapter {

    public RecommendAdapter(List mList, Context context) {
        super(mList, context);
    }

    boolean isshow;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemLick(int position);
    }
    @Override
    protected void bindData(Object o, VH vh, int position, Context context) {
        RecommendBean.DataBean.PostDetailBean datas = (RecommendBean.DataBean.PostDetailBean) o;
        ImageView imgHead = (ImageView) vh.getViewById(R.id.iv_user);
        ImageView imgIdentity = (ImageView) vh.getViewById(R.id.iv_identity);
        TextView tvAttention = (TextView) vh.getViewById(R.id.tv_attention);
        TextView tvDesc = (TextView) vh.getViewById(R.id.tv_desc);
        TextView tvUser = (TextView) vh.getViewById(R.id.tv_user);
        TextView txtTime = (TextView) vh.getViewById(R.id.tv_time);
        TextView tvShowMore = (TextView) vh.getViewById(R.id.tv_show_more);
        NineGridImageView niv = (NineGridImageView) vh.getViewById(R.id.niv);

        imgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemLick(position);
                }
            }
        });

        tvShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isshow) {
                    //isshow=false;
                    tvDesc.setEllipsize(TextUtils.TruncateAt.END);//收起
                    tvDesc.setLines(3);
                    tvShowMore.setTextColor(Color.BLUE);
                    tvShowMore.setText("展开");
                } else {
                    tvDesc.setEllipsize(null);//展开30
                    tvDesc.setSingleLine(false);//这个方法是必须设置的，否则无法展开
                    tvShowMore.setTextColor(Color.RED);
                    tvShowMore.setText("收起");

                }
                isshow = !isshow;
            }
        });

        Glide.with(context).load(datas.getHeadUrl()).apply(new RequestOptions().circleCrop()).into(imgHead);
        TxtUtils.setTextView(tvUser, datas.getNickName());

        Long time = DateUtils.getDateToTime(datas.getCreateTime(), null);
        String date = DateUtils.getStandardDate(time);
        TxtUtils.setTextView(txtTime, date);

        testSpannable(tvDesc, datas.getContent());


        List<RecommendBean.DataBean.PostDetailBean.ImagesBean> images = datas.getImages();
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            strings.add(images.get(i).getFilePath());
        }
        niv.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object object) {
                Glide.with(context).load(object).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(imageView);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                //点击查看大图
                super.onItemImageClick(context, index, list);
                Intent intent = new Intent(context, BigImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postion", index);
                bundle.putStringArrayList("urls", strings);
                intent.putExtra("data", bundle);
                context.startActivity(intent);
            }
        });
        niv.setImagesData(strings);
        RecommendBean.DataBean.PostDetailBean postDetailBean = new RecommendBean.DataBean.PostDetailBean();
        int i = new ClassUtils().imageClass(datas.getLevel());
        Glide.with(context).load(i).into(imgIdentity);
    }

    private void testSpannable(TextView tvDesc, String content) {
        int startPos = content.indexOf("#");
        int stopPos = content.lastIndexOf("#") + 1;

        int startUserPos = content.indexOf("@");
        int stopUserPos = content.lastIndexOf(" ");

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);

        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), startUserPos, stopUserPos, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        tvDesc.setMovementMethod(LinkMovementMethod.getInstance());
        tvDesc.setText(spannableStringBuilder);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_recommend_list;
    }
}
