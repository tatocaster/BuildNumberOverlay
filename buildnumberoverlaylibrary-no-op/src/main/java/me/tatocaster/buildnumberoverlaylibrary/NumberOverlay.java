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
	
	public static class Builder{
		
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
			
			
		}
		
		
		/**
		 * Set background color of canvas, otherwise it is used default background Color.BLACK
		 * @param backgroundColor color
		 * @return builder
		 */
		public Builder setBackgroundColor(int backgroundColor) {
			
			return this;
		}
		
		
		/**
		 * Set text color, otherwise it is used default Color.RED
		 * @param textColor text color
		 * @return builder
		 */
		public Builder setTextColor(int textColor) {
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
			
			return this;
		}
		
		
		/**
		 * Set gravity of canvas, otherwise it is used default value Gravity.BOTTOM | Gravity.END
		 * @param gravity gravity of canvas
		 * @return builder
		 */
		public Builder setGravity(int gravity) {
			return this;
		}
		
		
		/**
		 * Set height of canvas, otherwise it is used default value 300 pixels
		 * @param height canvas height in pixels
		 * @return builder
		 */
		public Builder setHeight(int height) {
			return this;
		}
		
		/**
		 * Set width of canvas, otherwise it is used default value 150 pixels
		 * @param width canvas width in pixels
		 * @return builder
		 */
		public Builder setWidth(int width) {
			return this;
		}
		
		/**
		 * Build set properties and initialize number overlay
		 */
		public void build(){
			
		}
	}

}
