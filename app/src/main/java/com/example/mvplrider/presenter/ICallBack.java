package com.example.mvplrider.presenter;

public interface ICallBack<T> {
    void onSuccess(T t);
    void onFial(String error);
}
