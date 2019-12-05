package com.example.componentization.model;

import android.app.Application;
import android.content.Context;

import com.example.componentization.WeatherContracts;
import com.example.componentization.base.BaseCommonLiveData;
import com.example.componentization.base.BaseErrorHandling;
import com.example.componentization.base.BaseViewModel;
import com.google.gson.Gson;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liMing
 * @Demo class WeatherModelImpl
 * @Description TODO
 * @date 2019-11-22 13:28
 */
public class WeatherModelImpl extends BaseViewModel<String> implements WeatherContracts.WeatherModel {

	public WeatherModelImpl(@NonNull Application application) {
		super(application);
	}

	@Override
	public WeatherModelImpl getWeather(String code) {
		final OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
		Request request = new Request.Builder().url("http://www.weather.com.cn/data/sk/" + code + ".html").build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
				//必须用 postValue      不能用 setValue   setValue 运行在主线程
				getData().postValue("");
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String json = response.body().toString();
				//解析成 bean
				//必须用 postValue      不能用 setValue   setValue 运行在主线程
				getData().postValue("aaaaaaaaaaaaaaa");
			}
		});
		return this;
	}

}
