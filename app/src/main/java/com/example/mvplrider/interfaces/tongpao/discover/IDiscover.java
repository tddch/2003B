package com.example.mvplrider.interfaces.tongpao.discover;

import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.presenter.IBasePresenter;
import com.example.mvplrider.presenter.IBaseView;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;

public interface IDiscover {
    interface View extends IBaseView {
        void getTopicBean(DiscoverTopicBean topicBean);

        void getTabBean(DiscoverTabBean tabBean);

    }

    interface Presenter extends IBasePresenter<View> {
        void getTopic();

        void getTab();

    }

    interface Model extends IModel{
        void loadTopic(ICallBack callBack);

        void loadTab(ICallBack callBack);

    }
}
