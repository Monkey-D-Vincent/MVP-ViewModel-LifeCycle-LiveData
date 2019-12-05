package com.example.componentization.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.componentization.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liMing
 * @Demo class BaseActivity
 * @Description TODO
 * @date 2019-11-20 16:36
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity {

	protected FragmentActivity activity;
	protected static String TAG;
	protected T presenter;
	private Unbinder binder;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		binder = ButterKnife.bind(this);
		setActivityTheme(0);
		TAG = getClass().getName();
		presenter = createPresenter();
		onCreateView();
		initView(savedInstanceState);
		getLifecycle().addObserver(presenter);
	}

	/**
	 * 设置刘海屏的适配模式
	 *
	 * @param type 0 这是一种默认的属性，在不进行明确指定的情况下，系统会自动使用这种属性。这种属性允许应用程序的内容在竖屏模式下自动延伸到刘海区域，而在横屏模式下则不会延伸到刘海区域 {@link WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT}
	 *             1 这种属性表示，不管手机处于横屏还是竖屏模式，都会允许应用程序的内容延伸到刘海区域 {@link WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES}
	 *             2 这种属性表示，永远不允许应用程序的内容延伸到刘海区域 {@link WindowManager.LayoutParams#LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER}
	 */
	protected void setActivityTheme(int type) {
		if (Build.VERSION.SDK_INT >= 28) {
			WindowManager.LayoutParams params = getWindow().getAttributes();
			if (type == 0) {
				params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
			} else if (type == 1) {
				params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
			} else {
				params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
			}
			getWindow().setAttributes(params);
		}
	}

	/**
	 * 设置安全区域
	 *
	 * @param viewGroup 父布局
	 * @param view      控件
	 */
	protected void setSafeArea(View viewGroup, View view) {
		if (Build.VERSION.SDK_INT >= 28) {
			viewGroup.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
				@Override
				public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
					DisplayCutout displayCutout = windowInsets.getDisplayCutout();
					if (displayCutout != null) {
						int left = displayCutout.getSafeInsetLeft();
						int top = displayCutout.getSafeInsetTop();
						int right = displayCutout.getSafeInsetRight();
						int bottom = displayCutout.getSafeInsetBottom();
						FrameLayout.LayoutParams topParams = (FrameLayout.LayoutParams) view.getLayoutParams();
						topParams.setMargins(left, top, right, bottom);
					}
					return windowInsets.consumeSystemWindowInsets();
				}
			});
		}
	}

	protected abstract void onCreateView();

	protected abstract void initView(@Nullable Bundle savedInstanceState);

	protected abstract T createPresenter();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(binder != null) {
			binder.unbind();
		}
	}
}
