package com.example.componentization.presenter;

import android.util.Log;

import com.example.componentization.WeatherContracts;
import com.example.componentization.base.BaseCommonLiveData;
import com.example.componentization.base.BasePresenter;
import com.example.componentization.model.WeatherModelImpl;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author liMing
 * @Demo class MainPresenter
 * @Description TODO
 * @date 2019-11-20 16:48
 */
public class MainPresenter extends BasePresenter<WeatherContracts.WeatherView> {

	private WeatherModelImpl model;

	public MainPresenter(@NotNull FragmentActivity context, @NotNull WeatherContracts.WeatherView view) {
		super(context, view);
		model = ViewModelProviders.of(context).get(WeatherModelImpl.class);
	}

	public void getWeather(String code) {
		model.getWeather(code).getData().setCommonLiveDataCall(new BaseCommonLiveData.CommonLiveDataCall() {
			@Override
			public void dataError(int errorCode, String msg) {
				getView().onFail(msg);
			}
		}).observe(getActivity(), new Observer<String>() {
			@Override
			public void onChanged(String s) {
				getView().onSuccess(s);
			}
		});
	}

	@Override
	public void attachView() {
		Log.i("MainPresenter", "attachView");
	}

	@Override
	public void deAttachView() {
		Log.i("MainPresenter", "deAttachView");
	}
}
