package com.example.mvplrider.interfaces.tongpao.discover;

import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.presenter.IBasePresenter;
import com.example.mvplrider.presenter.IBaseView;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;

public interface IDiscoverTab {
    interface View extends IBaseView {
        void getTabRvBean(DiscoverTabRvBean tabRvBean);
    }

    interface Presenter extends IBasePresenter<View> {

        void getTabRv(int type);
    }

    interface Model extends IModel{
        void loadTabRv(ICallBack callBack, int type);
    }
}
