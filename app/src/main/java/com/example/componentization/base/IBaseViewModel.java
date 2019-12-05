package com.example.componentization.base;

/**
 * @author liMing
 * @Demo class IBaseViewModel
 * @Description TODO
 * @date 2019-12-05 13:22
 */
public interface IBaseViewModel<T> {

	BaseCommonLiveData<T> getData();
}
