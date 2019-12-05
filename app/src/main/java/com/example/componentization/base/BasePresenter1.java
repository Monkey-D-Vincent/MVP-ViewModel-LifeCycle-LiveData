package com.example.componentization.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * @author liMing
 * @Demo class BasePresenter1
 * @Description TODO
 * @date 2019-12-02 14:29
 */
public class BasePresenter1<V extends BaseView> implements IPresenter<V> {

	private WeakReference<Context> mContext;
	private WeakReference<V> mView;

	public BasePresenter1(Context context, V view) {
		mContext = new WeakReference<>(context);
		mView = new WeakReference<>(view);
	}

	protected V getView() {
		return mView.get();
	}

	protected Context getContext() {
		return mContext.get();
	}

	protected boolean isViewAttached() {
		return mView.get() != null;
	}

	protected boolean isContextAttached() {
		return mContext.get() != null;
	}

	@Override
	public void attachView() {

	}

	@Override
	public void deAttachView() {

	}
}
