package me.tatocaster.buildnumberoverlaylibrary;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import me.tatocaster.buildnumberoverlaylibrary.exceptions.NumberOverlayException;
import me.tatocaster.buildnumberoverlaylibrary.utils.Constants;
import me.tatocaster.buildnumberoverlaylibrary.utils.Validation;

/**
 * Created by tatocaster on 1/28/17.
 */
@SuppressWarnings("unchecked")
public class NumberOverlay {
    private static final String TAG = NumberOverlay.class.getCanonicalName();
    private static Context applicationContext;

    private static final String MULTIPLE_INSTANCE_ERROR_STRING = "Can not initialize multiple times!";

    private static HashMap customs = new HashMap<>();

    /**
     * instance
     */
    private static NumberOverlay instance = null;
    private static boolean initialized = false;

    private NumberOverlay() {

    }

    /**
     * initialize this in Application.
     *
     * @param applicationContext The application context
     */

    private static synchronized void createInstance(Context applicationContext) {
        if (instance != null || initialized) {
            throw new NumberOverlayException(MULTIPLE_INSTANCE_ERROR_STRING);
        }
        initialized = true;
        instance = new NumberOverlay();
        NumberOverlay.applicationContext = applicationContext;

    }
    private static Intent getIntent() {
        return new Intent(getApplicationContext(), AccessPermissionActivity.class);
    }
    public static synchronized void initialize(Context applicationContext, final int backgroundColor, final int textColor) {
        createInstance(applicationContext);
        Intent intent = getIntent();
        customs.put(Constants.BACKGROUND_COLOR, backgroundColor);
        customs.put(Constants.TEXT_COLOR, textColor);
        customs.put(Constants.CANVAS_HEIGHT, Constants.DEFAULT_CANVAS_HEIGHT);
        customs.put(Constants.CANVAS_WIDTH, Constants.DEFAULT_CANVAS_WIDTH);
        intent.putExtra("customizations", customs);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    public static synchronized void initialize(final int CANVAS_HEIGHT, final int CANVAS_WIDTH, Context applicationContext) {
        createInstance(applicationContext);
        Intent intent = getIntent();
        customs.put(Constants.CANVAS_HEIGHT, CANVAS_HEIGHT);
        customs.put(Constants.CANVAS_WIDTH, CANVAS_WIDTH);
        customs.put(Constants.BACKGROUND_COLOR, Constants.DEFAULT_BACKGROUND);
        customs.put(Constants.TEXT_COLOR, Constants.DEFAULT_TEXT);
        intent.putExtra("customizations", customs);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    public static synchronized void initialize(Context applicationContext, final int backgroundColor, final int textColor,
                                               final int CANVAS_HEIGHT, final int CANVAS_WIDTH) {
        createInstance(applicationContext);
        Intent intent = getIntent();
        customs.put(Constants.BACKGROUND_COLOR, backgroundColor);
        customs.put(Constants.TEXT_COLOR, textColor);
        customs.put(Constants.CANVAS_HEIGHT, CANVAS_HEIGHT);
        customs.put(Constants.CANVAS_WIDTH, CANVAS_WIDTH);
        intent.putExtra("customizations", customs);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }




    /**
     * Indicates whether the app has been initialized.
     *
     * @return true if initialized, false if not
     */
    public static synchronized boolean isInitialized() {
        return initialized;
    }

    /**
     * The getter for the context of the current application.
     *
     * @return The context of the current application.
     */
    public static Context getApplicationContext() {
        Validation.sdkInitialized();
        return applicationContext;
    }

}
