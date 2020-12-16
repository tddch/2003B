package com.example.mvplrider.ui.tongpao.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseActivity;
import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigImageActivity extends BaseActivity {

    List<String> urls; //当前需要查看的所有图片的路径
    int currentPos; //当前操作的图片的位置
    @BindView(R.id.txt_return)
    TextView txtReturn;
    @BindView(R.id.txt_page)
    TextView txtPage;
    @BindView(R.id.txt_down)
    TextView txtDown;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ArrayList<String> imgs;
    private int index;

    @Override
    protected int getLayout() {
        return R.layout.activity_big_image;
    }

    @Override
    protected void initView() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
                updatePage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        imgs = bundle.getStringArrayList("urls");
        index = bundle.getInt("postion");

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view= LayoutInflater.from(BigImageActivity.this).inflate(R.layout.big_image_item,null);
                ImageView imageView = view.findViewById(R.id.iv_big_image);
                String s = imgs.get(position);
                Glide.with(view).load(s).into(imageView);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });
        viewPager.setCurrentItem(index);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //data存放图片数据和当前操作下标
        if (intent != null && intent.hasExtra("data")) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                urls = bundle.getStringArrayList("urls");
                currentPos = bundle.getInt("postion");
                updatePage();

            }
        }
    }
    @OnClick({R.id.txt_return, R.id.txt_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_return:
                break;
            case R.id.txt_down:
                break;
        }
    }

    //更新当前选中的图片位置
    private void updatePage(){
        txtPage.setText((index+1)+"/"+imgs.size()+"");

//        String page = String.valueOf(currentPos/urls.size());
//        TxtUtils.setTextView(txtPage,page);
        //判断是否有下载过
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void tips(String msg) {

    }

    @Override
    public void loading(int visible) {

    }

}