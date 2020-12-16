package com.example.mvplrider.model.tongpao.doscover;

import com.example.mvplrider.base.BaseModel;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscover;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscoverTab;
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
public class DiscoverTabModel extends BaseModel implements IDiscoverTab.Model {


    @Override
    public void loadTabRv(ICallBack callBack, int type) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getDiscoverTabRv(type)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<DiscoverTabRvBean>(callBack) {
                            @Override
                            public void onNext(DiscoverTabRvBean discoverTabRvBean) {
                                callBack.onSuccess(discoverTabRvBean);

                            }
                        })
        );
    }
}
