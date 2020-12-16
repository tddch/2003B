package com.example.mvplrider.presenter;

public interface IBasePresenter <V extends IBaseView>  {
    void attachView(V v);
    void unAttachView();
}
