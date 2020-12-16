package com.example.mvplrider.presenter;

import io.reactivex.disposables.Disposable;

/**
 *
 */
public interface IModel {
    void addDisposable(Disposable disposable);
    void clear();
}
