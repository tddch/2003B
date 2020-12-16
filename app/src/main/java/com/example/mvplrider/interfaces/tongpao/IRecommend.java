package com.example.mvplrider.interfaces.tongpao;

import com.example.mvplrider.model.tongpaodata.BannerBean;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.RecommendUserBean;
import com.example.mvplrider.model.tongpaodata.TopicBean;
import com.example.mvplrider.presenter.IBaseView;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;

import javax.security.auth.callback.Callback;

public interface IRecommend {

    interface View extends IBaseView {

        //定义一个被推荐页实现的View层接口方法
        void getRecommendReturn(RecommendBean result);
        //banner
        void getBannerReturn(BannerBean bannerBean);
        //热门用户
        void getUserReturn(RecommendUserBean userBean);
        //热门用户
        void getTopic(TopicBean topicBean);
    }

    interface Presenter extends IBasePersenter<View>{

        //定义一个首页推荐页面V层调用的接口
        void getRecommend();

        void getBanner();

        void getUser();

        void getTopic();
    }

    interface Model extends IModel {

        //定义一个加载推荐数据的接口方法 被P层
        void loadRecommend(ICallBack callback);

        void loadBanner(ICallBack callBack);

        void loadUser(ICallBack callBack);

        void loadTopic(ICallBack callBack);
    }


}