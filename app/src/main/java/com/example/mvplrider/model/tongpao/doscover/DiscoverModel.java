package com.example.mvplrider.model.tongpao.doscover;

import com.example.mvplrider.base.BaseModel;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscover;
import com.example.mvplrider.model.home.CommonSubscriber;
import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.net.HttpManager;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.utils.RxUtils;

/**
 *
 */
public class DiscoverModel extends BaseModel implements IDiscover.Model {
    @Override
    public void loadTopic(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTopic()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTopicBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTopicBean topicBean) {
                                callBack.onSuccess(topicBean);
                            }
                        })
        );
    }

    @Override
    public void loadTab(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTab()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTabBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTabBean tabBean) {
                                callBack.onSuccess(tabBean);
                            }
                        })
        );
    }


}
