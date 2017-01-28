package me.tatocaster.buildnumberoverlaylibrary.utils;

import java.util.Collection;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlay;
import me.tatocaster.buildnumberoverlaylibrary.exceptions.NumberOverlayException;

/**
 * Created by tatocaster on 1/28/17.
 */

public class Validation {
    private static final String TAG = Validation.class.getCanonicalName();


    public static void notNull(Object arg, String name) {
        if (arg == null) {
            throw new NullPointerException("Argument '" + name + "' cannot be null");
        }
    }

    public static <T> boolean isNullOrEmpty(Collection<T> c) {
        return (c == null) || (c.size() == 0);
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null) || (s.length() == 0);
    }

    public static void sdkInitialized() {
        if (!NumberOverlay.isInitialized())
            throw new NumberOverlayException("The library has not been initialized, make sure to call NumberOverlay.initialize() first.");

    }

}
