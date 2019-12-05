package com.example.componentization.base;

import androidx.lifecycle.MutableLiveData;

/**
 * @author liMing
 * @Demo class BaseCommonLiveData
 * @Description TODO ViewModel 数据类
 * @date 2019-12-05 11:47
 */
public class BaseCommonLiveData<T> extends MutableLiveData<T> {

	private BaseErrorHandling errorHandling;
	private CommonLiveDataCall commonLiveDataCall;

	public void setErrorHandling(BaseErrorHandling errorHandling) {
		this.errorHandling = errorHandling;
	}

	/**
	 * 异步处理
	 *
	 * @param value
	 */
	@Override
	public void postValue(T value) {
		//获取数据失败
		if (value == null) {
			setValue(BaseErrorHandling.NULLERROR, "无数据");
		} else {
			super.postValue(value);
		}
	}

	@Override
	public void setValue(T value) {
		//获取数据失败
		if (value == null) {
			setValue(BaseErrorHandling.NULLERROR, "无数据");
		} else {
			super.setValue(value);
		}
	}

	public BaseCommonLiveData<T> setCommonLiveDataCall(CommonLiveDataCall commonLiveDataCall) {
		this.commonLiveDataCall = commonLiveDataCall;
		return this;
	}

	public void setValue(int errorCode, String msg) {
		boolean handing = errorHandling.handing(errorCode);
		if (!handing) return;
		commonLiveDataCall.dataError(errorCode, msg);
	}

	public interface CommonLiveDataCall {
		void dataError(int errorCode, String msg);
	}

}
