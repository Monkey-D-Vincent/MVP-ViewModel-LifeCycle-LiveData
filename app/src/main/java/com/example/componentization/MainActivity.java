package com.example.componentization;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.widget.TextView;

import com.example.componentization.base.BaseActivity;
import com.example.componentization.presenter.MainPresenter;

public class MainActivity extends BaseActivity<WeatherContracts.WeatherView, MainPresenter> implements WeatherContracts.WeatherView {

	@Override
	protected void onCreateView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView(@Nullable Bundle savedInstanceState) {
		presenter.getWeather("101010100");
	}

	@Override
	public void onSuccess(final String json) {
		final TextView tvInfo = findViewById(R.id.tv_weather);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				tvInfo.setText(json);
			}
		});
	}

	@Override
	public void showProgressbar() {
		//显示 progressBar
	}

	@Override
	public void dismissProgressbar() {
		//隐藏 progressBar
	}

	@Override
	protected MainPresenter createPresenter() {
		return new MainPresenter(activity, this);
	}

	@Override
	public void onFail(String error) {

	}


}
