package com.example.mvplrider.presenter.tongpao.discover;

import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscover;
import com.example.mvplrider.model.tongpao.doscover.DiscoverModel;
import com.example.mvplrider.model.tongpaodata.DiscoverTabBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.model.tongpaodata.DiscoverTopicBean;
import com.example.mvplrider.presenter.ICallBack;

/**
 *
 */
public class DiscoverPresenter extends BasePresenter<IDiscover.View> implements IDiscover.Presenter {
    IDiscover.View view;
    IDiscover.Model model;
    public DiscoverPresenter(IDiscover.View view) {
        this.view = view;
        model=new DiscoverModel();
    }

    @Override
    public void getTopic() {
        this.model.loadTopic(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTopicBean((DiscoverTopicBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        });
    }

    @Override
    public void getTab() {
        this.model.loadTab(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getTabBean((DiscoverTabBean) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(error);
                }
            }
        });
    }


}
