package com.example.mvplrider.api;

import com.example.mvplrider.model.tongpaodata.BannerBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.RecommendUserBean;
import com.example.mvplrider.model.tongpaodata.TopicBean;
import com.example.mvplrider.model.tongpaodata.UserInfoData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 */
public interface TongpaoApi {
    String BASE_URL="http://cdwan.cn:7000/tongpao/";

    @GET("home/recommend.json")
    Flowable<RecommendBean> getRecommend();

    @GET("home/banner.json")
    Flowable<BannerBean> getBanner();

    @GET("home/hot_user.json")
    Flowable<RecommendUserBean> getRecommendUser();

    @GET("home/topic_discussed.json")
    Flowable<TopicBean> getTopic();


    @GET("home/personal.json")
    Flowable<UserInfoData> getUserInfo();

    @GET("discover/hot_activity.json")
    Flowable<DiscoverTopicBean> getDiscoverTopic();

    @GET("discover/navigation.json")
    Flowable<DiscoverTabBean> getDiscoverTab();

    @GET("discover/news_{type}.json")
    Flowable<DiscoverTabRvBean> getDiscoverTabRv(@Path("type") int type);

}
