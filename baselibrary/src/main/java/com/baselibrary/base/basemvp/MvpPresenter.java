package com.baselibrary.base.basemvp;

public interface MvpPresenter<V extends MVPBaseView> {


    void attachView(V view);


    void detachView();
}