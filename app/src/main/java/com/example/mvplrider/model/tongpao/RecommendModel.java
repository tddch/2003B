package com.example.mvplrider.model.tongpao;

import com.example.mvplrider.base.BaseModel;
import com.example.mvplrider.interfaces.tongpao.IRecommend;
import com.example.mvplrider.model.home.CommonSubscriber;
import com.example.mvplrider.model.tongpaodata.BannerBean;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.RecommendUserBean;
import com.example.mvplrider.model.tongpaodata.TopicBean;
import com.example.mvplrider.net.HttpManager;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.utils.RxUtils;

import javax.security.auth.callback.Callback;


public class RecommendModel extends BaseModel implements IRecommend.Model {


    @Override
    public void loadRecommend(ICallBack callback) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getRecommend()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendBean>(callback){

                            @Override
                            public void onNext(RecommendBean recommendBean) {
                                callback.onSuccess(recommendBean);
                            }
                        })
        );
    }

    @Override
    public void loadBanner(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getBanner()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BannerBean>(callBack){

                            @Override
                            public void onNext(BannerBean recommendBean) {
                                callBack.onSuccess(recommendBean);
                            }
                        })
        );
    }


    @Override
    public void loadUser(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getRecommendUser()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<RecommendUserBean>(callBack){

                            @Override
                            public void onNext(RecommendUserBean recommendUserBean) {
                                callBack.onSuccess(recommendUserBean);
                            }
                        })
        );
    }

    @Override
    public void loadTopic(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getTopic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TopicBean>(callBack){

                            @Override
                            public void onNext(TopicBean topicBean) {
                                callBack.onSuccess(topicBean);
                            }
                        })
        );
    }
}
