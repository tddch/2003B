package com.example.mvplrider.ui.tongpao.fragment.discover;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseFragment;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscover;
import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.presenter.tongpao.discover.DiscoverPresenter;
import com.example.mvplrider.ui.tongpao.fragment.recomment.RecommentTabFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class DiscoverFragment extends BaseFragment<DiscoverPresenter> implements IDiscover.View {

    @BindView(R.id.iv_paozi)
    ImageView ivPaozi;
    @BindView(R.id.iv_shetuan)
    ImageView ivShetuan;
    @BindView(R.id.iv_paihang)
    ImageView ivPaihang;
    @BindView(R.id.tv_paozi)
    TextView tvPaozi;
    @BindView(R.id.tv_shetuan)
    TextView tvShetuan;
    @BindView(R.id.tv_paihang)
    TextView tvPaihang;
    @BindView(R.id.rv_topic)
    RecyclerView rvTopic;
    @BindView(R.id.tab_discover)
    TabLayout tabDiscover;
    @BindView(R.id.vp_discover)
    ViewPager vpDiscover;
    private ArrayList<DiscoverTopicBean.DataBean> topicList;
    private TopicRvAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabTitle;

    @Override
    public int getLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        initTopic();
        initTab();
    }

    private void initTab() {
        fragments = new ArrayList<>();
        tabTitle = new ArrayList<>();
    }

    private void initTopic() {
        rvTopic.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        topicList = new ArrayList<>();
        adapter = new TopicRvAdapter(topicList, getActivity());
        rvTopic.setAdapter(adapter);
    }

    @Override
    public DiscoverPresenter createPresenter() {
        return new DiscoverPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getTopic();
        presenter.getTab();
    }

    @Override
    public void getTopicBean(DiscoverTopicBean topicBean) {
        topicList.addAll(topicBean.getData());
        adapter.notifyDataSetChanged();
        Log.e("TAG", "getTopicBean: " + topicBean.toString());
    }

    @Override
    public void getTabBean(DiscoverTabBean tabBean) {
        Log.e("TAG", "getTabBean: " + tabBean.toString());
        for (int i = 0; i < tabBean.getData().size()-1; i++) {
            fragments.add(new TabRvFragment(tabBean.getData().get(i).getType()));
            tabTitle.add(tabBean.getData().get(i).getName());
        }
        tabDiscover.setTabMode(TabLayout.MODE_SCROLLABLE);
        //初始化ViewPager
        vpDiscover.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
        tabDiscover.setupWithViewPager(vpDiscover);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }


    @OnClick({R.id.iv_paozi, R.id.iv_shetuan, R.id.iv_paihang, R.id.tv_paozi, R.id.tv_shetuan, R.id.tv_paihang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_paozi:
                break;
            case R.id.iv_shetuan:
                break;
            case R.id.iv_paihang:
                break;
            case R.id.tv_paozi:
                break;
            case R.id.tv_shetuan:
                break;
            case R.id.tv_paihang:
                break;
        }
    }
}
