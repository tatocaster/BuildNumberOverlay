package me.tatocaster.buildversionoverlay;

import android.app.Application;
import android.graphics.Color;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlay;

/**
 * Created by tatocaster on 1/28/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // initialize build number overlay like this
        NumberOverlay.initialize(this, Color.GRAY, Color.BLACK);
        // or like this
        /*NumberOverlay.initialize(this, 200, 100); */
        // or even like this
        /* NumberOverlay.initialize(this, Color.GREEN, Color.WHITE, 200, 100); */
    }
}
