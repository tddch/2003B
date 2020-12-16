package com.example.mvplrider.base;

import com.example.mvplrider.presenter.IBasePresenter;
import com.example.mvplrider.presenter.IBaseView;

import java.lang.ref.WeakReference;

/**
 *
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;
    WeakReference<V> weakReference;

    @Override
    public void attachView(V v) {
        weakReference = new WeakReference<V>(v);
        mView=weakReference.get();
    }

    @Override
    public void unAttachView() {
        mView=null;
    }
}
