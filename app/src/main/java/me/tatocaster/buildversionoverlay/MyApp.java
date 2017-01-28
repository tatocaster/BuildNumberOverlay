package me.tatocaster.buildversionoverlay;

import android.app.Application;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlay;

/**
 * Created by tatocaster on 1/28/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // initialize build number overlay
        NumberOverlay.initialize(this);
    }
}
