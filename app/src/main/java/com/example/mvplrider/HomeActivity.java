package com.example.mvplrider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvplrider.jpush.TestJPushActivity;
import com.example.mvplrider.ui.huanxin.HuanxinActivity;
import com.example.mvplrider.ui.map.FaoDeMapActivity;
import com.example.mvplrider.ui.map.MapActivity;
import com.example.mvplrider.ui.map.MarkerActivity;
import com.example.mvplrider.ui.tongpao.TongpaoActivity;
import com.example.mvplrider.ui.tongpao.fragment.CoordinatorLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.btn_tongapao)
    Button btnTongapao;
    @BindView(R.id.btn_baidu)
    Button btnBaidu;
    @BindView(R.id.btn_jpush)
    Button btnJpush;
    @BindView(R.id.btn_gaode)
    Button btnGaode;
    @BindView(R.id.btn_view)
    Button btn_View;
    @BindView(R.id.btn_marker)
    Button btnMarker;
    @BindView(R.id.btn_huanxin)
    Button btnHuanxin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_tongapao, R.id.btn_baidu, R.id.btn_view, R.id.btn_jpush, R.id.btn_gaode, R.id.btn_marker,R.id.btn_huanxin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tongapao:
                startActivity(new Intent(HomeActivity.this, TongpaoActivity.class));
                break;
            case R.id.btn_view:
                startActivity(new Intent(HomeActivity.this, CoordinatorLayoutActivity.class));
                break;
            case R.id.btn_jpush:
                startActivity(new Intent(HomeActivity.this, TestJPushActivity.class));
                break;
            case R.id.btn_baidu:
                startActivity(new Intent(HomeActivity.this, MapActivity.class));
                break;
            case R.id.btn_gaode:
                startActivity(new Intent(HomeActivity.this, FaoDeMapActivity.class));
                break;
            case R.id.btn_marker:
                startActivity(new Intent(HomeActivity.this, MarkerActivity.class));
                break;
            case R.id.btn_huanxin:
                startActivity(new Intent(HomeActivity.this, HuanxinActivity.class));
                break;
        }
    }
}