package com.example.mvplrider.model.tongpao;

import android.service.autofill.UserData;

import com.example.mvplrider.base.BaseModel;
import com.example.mvplrider.interfaces.tongpao.IUserInfo;
import com.example.mvplrider.model.home.CommonSubscriber;
import com.example.mvplrider.model.tongpaodata.UserInfoData;
import com.example.mvplrider.net.HttpManager;
import com.example.mvplrider.presenter.ICallBack;
import com.example.mvplrider.presenter.IModel;
import com.example.mvplrider.utils.RxUtils;

import javax.security.auth.callback.Callback;

/**
 *
 */
public class UserInfoModel extends BaseModel implements IUserInfo.Model {


    @Override
    public void loadUser(ICallBack callBack) {
        addDisposable(
                HttpManager.getInstance().tongpaoApi().getUserInfo()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<UserInfoData>(callBack) {
                            @Override
                            public void onNext(UserInfoData userInfoData) {
                                callBack.onSuccess(userInfoData);
                            }
                        })
        );
    }
}
