package com.example.mvplrider.presenter.home;

import com.example.mvplrider.model.data.CityData;
import com.example.mvplrider.presenter.IBasePresenter;
import com.example.mvplrider.presenter.IBaseView;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;

/**
 *
 */
public interface IHome {
    interface View extends IBaseView{
        void getCityReturn(CityData cityData);
    }

    interface Model extends IModel{
        void getCity(ICallBack callBack);

    }

    interface Presenter extends IBasePresenter<View>{
        void getCity();
    }
}
