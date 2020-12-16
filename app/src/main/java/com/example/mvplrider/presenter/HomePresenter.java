package com.example.mvplrider.presenter;

import android.view.View;

import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.model.data.CityData;
import com.example.mvplrider.model.home.HomeModel;
import com.example.mvplrider.presenter.home.IHome;

/**
 *
 */
public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {
    IHome.View view;
    IHome.Model model;
    public HomePresenter(IHome.View view) {
        this.view = view;
        model=new HomeModel();
    }


    @Override
    public void getCity() {
        this.view.loading(View.VISIBLE);
        this.model.getCity(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.loading(View.GONE);
                    view.getCityReturn((CityData) o);
                }
            }

            @Override
            public void onFial(String error) {
                if(view!=null){
                    view.tips(""+error);
                    view.loading(View.GONE);
                }
            }
        });
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
        if(model!=null){
            model.clear();
        }
    }
}
