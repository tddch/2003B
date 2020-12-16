package com.example.mvplrider.presenter.tongpao.discover;

import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscover;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscoverTab;
import com.example.mvplrider.model.tongpao.doscover.DiscoverModel;
import com.example.mvplrider.model.tongpao.doscover.DiscoverTabModel;
import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.presenter.ICallBack;

/**
 *
 */
public class DiscoverTabPresenter extends BasePresenter<IDiscoverTab.View> implements IDiscoverTab.Presenter {
    IDiscoverTab.View view;
    IDiscoverTab.Model model;
    public DiscoverTabPresenter(IDiscoverTab.View view) {
        this.view = view;
        model=new DiscoverTabModel();
    }


    @Override
    public void getTabRv(int type) {
        this.model.loadTabRv(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTabRvBean((DiscoverTabRvBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        },type);
    }
}
