package com.example.mvplrider.ui.tongpao.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvplrider.R;
import com.example.mvplrider.adapter.DiscussedAdapter;
import com.example.mvplrider.adapter.RecommendAdapter;
import com.example.mvplrider.base.BaseAdapter;
import com.example.mvplrider.base.BaseFragment;
import com.example.mvplrider.interfaces.tongpao.IRecommend;
import com.example.mvplrider.model.tongpaodata.BannerBean;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.RecommendUserBean;
import com.example.mvplrider.model.tongpaodata.TopicBean;
import com.example.mvplrider.model.tongpaodata.homeGoodsBean;
import com.example.mvplrider.presenter.tongpao.RecommendPersenter;
import com.example.mvplrider.ui.tongpao.activity.UserInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 *
 */
public class RecommendFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_topic_discussed)
    RecyclerView rvTopicDiscussed;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.rv_hot_user)
    RecyclerView rvHotUser;
    private List<TopicBean.DataBean> topicList;
    private DiscussedAdapter adapter;
    private List<RecommendBean.DataBean.PostDetailBean> recommendList;
    private RecommendAdapter recommendAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public RecommendPersenter createPresenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initView() {
        topicList = new ArrayList<>();
        adapter = new DiscussedAdapter(topicList, getActivity());
        rvTopicDiscussed.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvTopicDiscussed.setAdapter(adapter);

        recommendList = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(recommendList, getActivity());
        rvRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRecommend.setAdapter(recommendAdapter);

        recommendAdapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemLick(int position) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void initData() {
        presenter.getRecommend();
        presenter.getBanner();
        presenter.getUser();
        presenter.getTopic();

    }

    @Override
    public void getRecommendReturn(RecommendBean result) {
//        Log.e("TAG1", "getRecommendReturn: " + result.getData().getPostDetail().toString());
        recommendList.add(result.getData().getPostDetail());
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBannerReturn(BannerBean bannerBean) {
//        Log.e("TAG", "getBannerReturn: " + bannerBean.getData().getList().toString());

        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> image = new ArrayList<>();

        List<BannerBean.DataBean.ListBean> bannerList = bannerBean.getData().getList();

        for (int i = 0; i < bannerList.size(); i++) {
            title.add(bannerList.get(i).getName());
            image.add(bannerList.get(i).getBanner());
        }

        banner.setAdapter(new BannerImageAdapter<BannerBean.DataBean.ListBean>(bannerList) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerBean.DataBean.ListBean data, int position, int size) {
                Glide.with(holder.imageView).load(data.getBanner())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .setScrollTime(3000)
                .isAutoLoop(true)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()))
                .start();
//                .setImages(image)
//                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
//                .setImageLoader(new ImageLoader() {
//                    @Override
//                    public void displayImage(Context context, Object path, ImageView imageView) {
//                        RequestOptions requestOptions=new RequestOptions();
//                        requestOptions.transform(new RoundedCorners(30));
//                        Glide.with(context).load(path).apply(requestOptions).into(imageView);
//                    }
//                })
//                .start();
    }

    @Override
    public void getUserReturn(RecommendUserBean userBean) {
//        Log.e("TAG", "RecommendUserBean: " + userBean.getData().toString());

    }

    @Override
    public void getTopic(TopicBean topicBean) {
//       Log.e("TAG", "TopicBean: " + topicBean.getData().toString());
        if (topicBean != null) {
            topicList.clear();
        }
        topicList.addAll(topicBean.getData());
        adapter.notifyDataSetChanged();
    }
}
