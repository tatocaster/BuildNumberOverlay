package me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    private static final int CANVAS_WIDTH = 300;
    private static final int CANVAS_HEIGHT = 150;
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

    public void addToWindowManager() {
        WindowManager.LayoutParams windowLayoutParams = new WindowManager.LayoutParams(
                CANVAS_WIDTH,
                CANVAS_HEIGHT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        windowLayoutParams.gravity = Gravity.BOTTOM | Gravity.END;

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setBackgroundColor(Color.BLACK);
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mLinearLayout, windowLayoutParams);

        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setText(
                String.format("Name: %s \n Code: %s",
                        mPackageInfo.versionName,
                        mPackageInfo.versionCode)
        );
        mLinearLayout.addView(textView);
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        mWindowManager.removeView(mLinearLayout);
    }
}
