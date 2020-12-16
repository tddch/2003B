package com.example.mvplrider.interfaces.tongpao;

import android.view.View;

import com.example.mvplrider.model.tongpaodata.UserInfoData;
import com.example.mvplrider.presenter.IBasePresenter;
import com.example.mvplrider.presenter.IBaseView;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;

/**
 *
 */
public interface IUserInfo {
    interface IView extends IBaseView{
        void getUserReturn(UserInfoData userInfoData);
    }

    interface IPresenter extends IBasePresenter {
        void getUser();

    }
    interface Model extends IModel{
        void loadUser(ICallBack callBack);
    }
}
