package com.example.mvplrider.presenter.tongpao;

import com.example.mvplrider.base.BasePresenter;
import com.example.mvplrider.interfaces.tongpao.IUserInfo;
import com.example.mvplrider.model.tongpao.UserInfoModel;
import com.example.mvplrider.model.tongpaodata.UserInfoData;
import com.example.mvplrider.presenter.ICallBack;

/**
 *
         */
public class UserInfoPresenter  extends BasePresenter implements IUserInfo.IPresenter {
    IUserInfo.IView view;
    IUserInfo.Model model;
    public UserInfoPresenter(IUserInfo.IView view) {
        this.view = view;
        model=new UserInfoModel();
    }

    @Override
    public void getUser() {
        this.model.loadUser(new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                if(view!=null){
                    view.getUserReturn((UserInfoData) o);
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
