package me.tatocaster.buildnumberoverlaylibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView.OverlayView;
import me.tatocaster.buildnumberoverlaylibrary.exceptions.NumberOverlayException;
import me.tatocaster.buildnumberoverlaylibrary.utils.Validation;

/**
 * Created by tatocaster on 1/28/17.
 */

/**
 *
 * Example of use:
 * <code>
 *    <p>new NumberOverlay.Builder(this)</p>
 *     <p>.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL)</p>
 *     <p>.setText(String.format("DEBUG VERSION"))</p>
 *     <p>.setTextColor(Color.YELLOW)</p>
 *     <p>.setBackgroundColor(Color.BLUE)</p>
 *     <p>.build();</p>
 * </code>
 */
public class NumberOverlay {
    private static final String TAG = NumberOverlay.class.getCanonicalName();
    private static Context applicationContext;

    private static final String MULTIPLE_INSTANCE_ERROR_STRING = "Can not initialize multiple times!";

	

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
     * @param builder
     */
    private static synchronized void initialize(Context applicationContext, Builder builder) {
        if (instance != null || initialized) {
            throw new NumberOverlayException(MULTIPLE_INSTANCE_ERROR_STRING);
        }
        initialized = true;
        instance = new NumberOverlay();
        NumberOverlay.applicationContext = applicationContext;

        Intent intent = AccessPermissionActivity.getCallingIntent(getApplicationContext(), builder.getProperties());
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
	
	
	/**
	 *
	 */
	public static class Builder{
	
	
	    private static final int CANVAS_WIDTH = 300;
	    private static final int CANVAS_HEIGHT = 150;
	    private static final int GRAVITY = Gravity.BOTTOM | Gravity.END;
	    private static final String TEXT = String.format("Name: %s \n Code: %s",
			    BuildConfig.VERSION_NAME,
			    BuildConfig.VERSION_CODE);
	    private static final int TEXT_COLOR = Color.RED;
	    private static final int BACKGROUND_COLOR = Color.BLACK;
	    
	    private OverlayView.Properties properties;
	    private Context context;
		
		
		/**
		 * Builder for {@link NumberOverlay}. If you want to initialize call method {@link Builder#build()}
		 * Example of use:
		 * <code>
		 *    <p>new NumberOverlay.Builder(this)</p>
		 *     <p>.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL)</p>
		 *     <p>.setText(String.format("DEBUG VERSION"))</p>
		 *     <p>.setTextColor(Color.YELLOW)</p>
		 *     <p>.setBackgroundColor(Color.BLUE)</p>
		 *     <p>.build();</p>
		 * </code>
		 * @param context application
		 */
	    public Builder(Context context) {
		    this.context = context;
		    properties = new OverlayView.Properties();
	    }
	
	
	    /**
	     * Set background color of canvas, otherwise it is used default background Color.BLACK
	     * @param backgroundColor color
	     * @return builder
	     */
	    public Builder setBackgroundColor(int backgroundColor) {
		    this.properties.setBackgroundColor(backgroundColor);
		    return this;
	    }
	
	
	    /**
	     * Set text color, otherwise it is used default Color.RED
	     * @param textColor text color
	     * @return builder
	     */
	    public Builder setTextColor(int textColor) {
		    this.properties.setTextColor(textColor);
		    return this;
	    }
	
	
	    /**
	     * Set formatted text, otherwise it is used default text with build number and build code.
	     * <code>
	     *     <p>Name: version name</p>
	     *     <p>Code: version code</p>
	     * </code>
	     * @param text text to set
	     * @return builder
	     */
	    public Builder setText(String text) {
		    this.properties.setText(text);
		    return this;
	    }
	
	
	    /**
	     * Set gravity of canvas, otherwise it is used default value Gravity.BOTTOM | Gravity.END
	     * @param gravity gravity of canvas
	     * @return builder
	     */
	    public Builder setGravity(int gravity) {
		    this.properties.setGravity(gravity);
		    return this;
	    }
	
	
	    /**
	     * Set height of canvas, otherwise it is used default value {@value CANVAS_HEIGHT} pixels
	     * @param height canvas height in pixels
	     * @return builder
	     */
	    public Builder setHeight(int height) {
		    this.properties.setCanvasHeight(height);
		    return this;
	    }
	
	    /**
	     * Set width of canvas, otherwise it is used default value {@value CANVAS_WIDTH} pixels
	     * @param width canvas width in pixels
	     * @return builder
	     */
	    public Builder setWidth(int width) {
		    this.properties.setCanvasWidth(width);
		    return this;
	    }
	
	
	    OverlayView.Properties getProperties() {
		    return properties;
	    }
	
	    /**
	     * Build set properties and initialize number overlay
	     */
	    public void build(){
		    if (properties.getCanvasWidth() == null) {
			    properties.setCanvasWidth(CANVAS_WIDTH);
		    }
		    
		    if (properties.getCanvasHeight() == null) {
			    properties.setCanvasHeight(CANVAS_HEIGHT);
		    }
		    
		    if (properties.getBackgroundColor() == null) {
			    properties.setBackgroundColor(BACKGROUND_COLOR);
		    }
		    
		    if (properties.getTextColor() == null) {
			    properties.setTextColor(TEXT_COLOR);
		    }
		
		    if (properties.getText() == null) {
			    properties.setText(TEXT);
		    }
		    
		    if (properties.getGravity() == null) {
			    properties.setGravity(GRAVITY);
		    }
		    
		    NumberOverlay.initialize(context, this);
	    }
    }
	

}
