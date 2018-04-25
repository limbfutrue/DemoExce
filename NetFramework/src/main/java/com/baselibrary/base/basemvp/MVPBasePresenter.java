package com.baselibrary.base.basemvp;

import java.lang.ref.WeakReference;

/**
 * Created by Libaoming on 24/4/2018.
 * 15 hour 10 minute
 * project_name : DemoExce
 */

public class MVPBasePresenter<V extends MVPBaseView> implements MvpPresenter<V>{
    private WeakReference<V> viewRef;
    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
