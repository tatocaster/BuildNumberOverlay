package me.tatocaster.buildnumberoverlaylibrary;

import android.content.Context;

/**
 * Created by tatocaster on 1/28/17.
 */

/**
 * A no-op version of library that can be used in release builds.
 */
public class NumberOverlay {

    private NumberOverlay() {
        throw new AssertionError();
    }

    /**
     * initialize this in Application.
     *
     * @param applicationContext The application context
     */
    public static synchronized void initialize(Context applicationContext) {

    }


    /**
     * Indicates whether the app has been initialized.
     *
     * @return true if initialized, false if not
     */
    public static synchronized boolean isInitialized() {
        return false;
    }

    /**
     * The getter for the context of the current application.
     *
     * @return The context of the current application.
     */
    public static Context getApplicationContext() {
        return null;
    }

}
