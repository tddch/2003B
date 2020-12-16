package com.example.mvplrider.ui.tongpao.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseActivity;
import com.example.mvplrider.interfaces.tongpao.IUserInfo;
import com.example.mvplrider.model.tongpaodata.UserInfoData;
import com.example.mvplrider.presenter.tongpao.UserInfoPresenter;
import com.example.mvplrider.utils.ClassUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements IUserInfo.IView {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vsha)
    TextView vsha;
    @BindView(R.id.iv_header_a)
    ImageView ivHeaderA;
    @BindView(R.id.name_per)
    TextView namePer;
    @BindView(R.id.iv_level_per)
    ImageView ivLevelPer;
    @BindView(R.id.tv_lt)
    TextView tvLt;
    @BindView(R.id.tv_bj)
    TextView tvBj;
    @BindView(R.id.tv_gz)
    TextView tvGz;
    @BindView(R.id.grjj)
    TextView grjj;
    @BindView(R.id.num_myContact)
    TextView numMyContact;
    @BindView(R.id.num_exp)
    TextView numExp;
    @BindView(R.id.num_fans)
    TextView numFans;
    @BindView(R.id.tv_myContact)
    TextView tvMyContact;
    @BindView(R.id.tv_exp)
    TextView tvExp;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.ctl1)
    ConstraintLayout ctl1;
    @BindView(R.id.ll)
    ConstraintLayout ll;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.nl)
    NestedScrollView nl;
    @BindView(R.id.cl)
    CoordinatorLayout cl;
    @BindView(R.id.iv_shui)
    ImageView ivShui;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getUser();
    }

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }


    @Override
    public void getUserReturn(UserInfoData userInfoData) {
        UserInfoData.DataBean data = userInfoData.getData();

        Glide.with(this).load(data.getHeadUrl()).apply(new RequestOptions().circleCrop()).into(ivHeaderA);
        int aClass = new ClassUtils().imageClass(data.getLevel());
        Glide.with(this).load(aClass).into(ivLevelPer);

        tvBj.setText(data.getNickName());
        tvExp.setText(data.getSignature());
    }


    @Override
    public void tips(String msg) {

    }

    @Override
    public void loading(int visible) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_shui)
    public void onClick() {
        
    }
}