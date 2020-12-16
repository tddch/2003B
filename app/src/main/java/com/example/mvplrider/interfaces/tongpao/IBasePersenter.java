package com.example.mvplrider.interfaces.tongpao;

import com.example.mvplrider.presenter.IBaseView;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();


}