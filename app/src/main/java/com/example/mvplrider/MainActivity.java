package com.example.mvplrider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvplrider.base.BaseActivity;
import com.example.mvplrider.model.data.CityData;
import com.example.mvplrider.presenter.HomePresenter;
import com.example.mvplrider.presenter.home.IHome;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<HomePresenter> implements IHome.View {


    @BindView(R.id.tv)
    TextView tv;
    private HomePresenter homePresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        homePresenter.getCity();
    }

    @Override
    protected HomePresenter createPresenter() {
        homePresenter = new HomePresenter(this);
        return homePresenter;
    }

    @Override
    public void getCityReturn(CityData cityData) {
        Log.e("TAG", "getCityReturn: "+cityData.getResult().toString() );
        tv.setText(cityData.getResult().toString());
    }

    @Override
    public void tips(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loading(int visible) {

    }




}