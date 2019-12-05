package com.example.componentization.base

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference

/**
 * @Demo class BasePresenter
 * @Description TODO
 * @author libo
 * @date 2019-11-21 17:05
 */
abstract class BasePresenter<V : BaseView>(activity: FragmentActivity, view: V) : IPresenter<V>, LifecycleObserver {

    private var mActivity: WeakReference<FragmentActivity> = WeakReference(activity)
    private var mView: WeakReference<V> = WeakReference(view)

    protected fun getActivity(): FragmentActivity? {
        return mActivity.get()
    }

    protected fun getView(): V? {
        return mView.get()
    }

    protected fun isViewAttached(): Boolean {
        return mView.get() != null
    }

    abstract override fun attachView()

    abstract override fun deAttachView()
}