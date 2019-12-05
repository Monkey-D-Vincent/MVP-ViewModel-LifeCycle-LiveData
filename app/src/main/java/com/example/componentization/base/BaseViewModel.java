package com.example.componentization.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * @author liMing
 * @Demo class BaseViewModel
 * @Description TODO
 * @date 2019-12-05 13:20
 */
public class BaseViewModel<T> extends AndroidViewModel implements IBaseViewModel<T> {

	private BaseCommonLiveData<T> liveData;

	public BaseViewModel(@NonNull Application application) {
		super(application);
		liveData = new BaseCommonLiveData<>();
		liveData.setErrorHandling(new BaseErrorHandling());
	}

	@Override
	public BaseCommonLiveData<T> getData() {
		return liveData;
	}

}
