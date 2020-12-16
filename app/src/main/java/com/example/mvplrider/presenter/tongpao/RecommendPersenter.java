package com.example.mvplrider.presenter.tongpao;

import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.interfaces.tongpao.IRecommend;
import com.example.mvplrider.model.tongpao.RecommendModel;
import com.example.mvplrider.model.tongpaodata.BannerBean;
import com.example.mvplrider.model.tongpaodata.RecommendBean;
import com.example.mvplrider.model.tongpaodata.RecommendUserBean;
import com.example.mvplrider.model.tongpaodata.TopicBean;
import com.example.mvplrider.presenter.ICallBack;

public class RecommendPersenter extends BasePresenter<IRecommend.View> implements IRecommend.Presenter {

    IRecommend.View view;
    IRecommend.Model model;

    public RecommendPersenter(IRecommend.View view){
        this.view = view;
        this.model = new RecommendModel();
    }

    @Override
    public void getRecommend() {
        this.model.loadRecommend(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view != null){
                    view.getRecommendReturn((RecommendBean) o);
                }

            }

            @Override
            public void onFial(String error) {
                if(view != null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void getBanner() {
        this.model.loadBanner(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view != null){
                    view.getBannerReturn((BannerBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view != null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void getUser() {
        this.model.loadUser(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view != null){
                    view.getUserReturn((RecommendUserBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view != null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void getTopic() {
        this.model.loadTopic(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view != null){
                    view.getTopic((TopicBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view != null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void attachView(IRecommend.View view) {

    }

    @Override
    public void unAttachView() {
//释放当前页面还未完成的网络请求
        if(model != null){
            model.clear();
        }
    }
}
