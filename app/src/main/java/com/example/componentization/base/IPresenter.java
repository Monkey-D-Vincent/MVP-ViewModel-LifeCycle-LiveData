package com.example.componentization.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author liMing
 * @Demo class IPresenter
 * @Description TODO
 * @date 2019-11-20 16:48
 */
public interface IPresenter<V extends BaseView> extends LifecycleObserver {

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	void attachView();

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	void deAttachView();
}
