package com.example.mvplrider.model.home;

import com.example.mvplrider.base.BaseModel;
import com.example.mvplrider.model.data.CityData;
import com.example.mvplrider.net.HttpManager;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.home.IHome;
import com.example.mvplrider.utils.RxUtils;

import javax.sql.CommonDataSource;

import io.reactivex.disposables.Disposable;

/**
 *
 */
public class HomeModel extends BaseModel implements IHome.Model {
    @Override
    public void getCity(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().apiService().getCity()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CityData>(callBack){
            @Override
            public void onNext(CityData cityData) {
                callBack.onSuccess(cityData);
            }
        }));

    }
}
