package me.tatocaster.buildversionoverlay;

import android.app.Application;
import android.graphics.Color;
import android.view.Gravity;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlay;

/**
 * Created by tatocaster on 1/28/17.
 */

public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		// initialize build number overlay
		new NumberOverlay.Builder(this)
				.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL)
				.setText(String.format("Name: %s \n Code: %s",
						BuildConfig.VERSION_NAME,
						BuildConfig.VERSION_CODE))
				.setTextColor(Color.YELLOW)
				.setBackgroundColor(Color.BLUE)
				.setWidth(300)
				.setHeight(200)
				.build();
	}
}
