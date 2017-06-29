package me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

import me.tatocaster.buildnumberoverlaylibrary.utils.Utils;

/**
 * Created by tatocaster on 1/28/17.
 */

public class OverlayView extends View {
    private static final String TAG = "OverlayView";

    /**
     * application context
     */
    private Context mContext;


    private WindowManager mWindowManager;
    private LinearLayout mLinearLayout;

    /**
     * main package info
     */
    private PackageInfo mPackageInfo;


    public OverlayView(Context context) {
        super(context);
        init(context);
    }

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPackageInfo = Utils.getVersionInfo(mContext);
    }

    public void addToWindowManager(Properties properties) {
        WindowManager.LayoutParams windowLayoutParams = new WindowManager.LayoutParams(
                properties.getCanvasWidth(),
                properties.getCanvasHeight(),
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        windowLayoutParams.gravity = properties.getGravity();

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setBackgroundColor(properties.getBackgroundColor());
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mLinearLayout, windowLayoutParams);

        TextView textView = new TextView(mContext);
        textView.setTextColor(properties.getTextColor());
        textView.setText(properties.getText());
        mLinearLayout.addView(textView);
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        mWindowManager.removeView(mLinearLayout);
    }
    
    public static class Properties implements Serializable{
	    private Integer backgroundColor = null;
	    private Integer textColor = null;
	    private String text = null;
	    private Integer gravity = null;
	    private Integer canvasHeight = null;
	    private Integer canvasWidth = null;
	
	    public Integer getBackgroundColor() {
		    return backgroundColor;
	    }
	
	    public Integer getTextColor() {
		    return textColor;
	    }
	
	    public String getText() {
		    return text;
	    }
	
	    public Integer getGravity() {
		    return gravity;
	    }
	
	    public Integer getCanvasHeight() {
		    return canvasHeight;
	    }
	
	    public Integer getCanvasWidth() {
		    return canvasWidth;
	    }
	
	    public void setBackgroundColor(int color) {
		    this.backgroundColor = color;
	    }
	
	    public void setTextColor(int textColor) {
		    this.textColor = textColor;
	    }
	
	    public void setText(String text) {
		    this.text = text;
	    }
	
	    public void setGravity(int gravity) {
		    this.gravity = gravity;
	    }
	
	    public void setCanvasHeight(int canvasHeight) {
		    this.canvasHeight = canvasHeight;
	    }
	
	    public void setCanvasWidth(int canvasWidth) {
		    this.canvasWidth = canvasWidth;
	    }
    }
}
