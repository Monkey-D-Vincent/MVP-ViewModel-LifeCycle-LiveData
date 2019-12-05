package com.example.componentization;

import com.example.componentization.base.BaseView;
import com.example.componentization.base.BaseViewModel;

/**
 * @author liMing
 * @Demo class WeatherContracts
 * @Description TODO
 * @date 2019-11-22 13:23
 */
public interface WeatherContracts {

	interface WeatherView extends BaseView {
		void onFail(String error);

		void onSuccess(String json);
	}

	interface WeatherModel {
		BaseViewModel getWeather(String code);
	}

}
