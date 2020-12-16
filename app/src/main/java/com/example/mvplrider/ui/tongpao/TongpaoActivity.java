package com.example.mvplrider.ui.tongpao;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mvplrider.R;
import com.example.mvplrider.statusbar.StatusBarUtils;
import com.example.mvplrider.ui.tongpao.fragment.RecommendFragment;
import com.example.mvplrider.ui.tongpao.fragment.discover.DiscoverFragment;
import com.example.mvplrider.ui.tongpao.fragment.recomment.RecommentTabFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TongpaoActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    String[] tabName = {"首页", "发现", "视频", "摄影", "知识文章"};
    @BindView(R.id.fl)
    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongpao);
        //沉浸式状态栏
        StatusBarUtils.setColor(this, 0x000000);
        StatusBarUtils.setTextDark(this, true);

        ButterKnife.bind(this);

        initView();
    }


    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DiscoverFragment discoverFragment = new DiscoverFragment();
        RecommentTabFragment recommendFragment = new RecommentTabFragment();
        fragmentManager.beginTransaction().add(R.id.fl,recommendFragment)
                .add(R.id.fl,discoverFragment)
                .show(recommendFragment)
                .hide(discoverFragment)
                .commit();

//        tabLayout.getTabAt(0).setText("首页");
//        tabLayout.getTabAt(1).setText("发现");
        tabLayout.addTab(tabLayout.newTab().setText("首页"));
        tabLayout.addTab(tabLayout.newTab().setText("发现"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentManager.beginTransaction()
                                .show(recommendFragment)
                                .hide(discoverFragment)
                                .commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction()
                                .show(discoverFragment)
                                .hide(recommendFragment)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}