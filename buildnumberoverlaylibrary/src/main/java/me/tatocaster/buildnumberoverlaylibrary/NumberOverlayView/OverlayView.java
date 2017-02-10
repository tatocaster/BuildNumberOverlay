package me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.tatocaster.buildnumberoverlaylibrary.R;
import me.tatocaster.buildnumberoverlaylibrary.exceptions.OutOfBoundsException;
import me.tatocaster.buildnumberoverlaylibrary.utils.Constants;
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

    private int CANVAS_WIDTH = Constants.DEFAULT_CANVAS_WIDTH;
    private int CANVAS_HEIGHT = Constants.DEFAULT_CANVAS_HEIGHT;
    private int backgroundColor = Constants.DEFAULT_BACKGROUND;
    private int textColor = Constants.DEFAULT_TEXT;
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

    public OverlayView(Context context, int backgroundColor, int textColor) {
        super(context);
        init(context);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public OverlayView(int CANVAS_HEIGHT, int CANVAS_WIDTH, Context context) {
        super(context);
        init(context);
        this.CANVAS_HEIGHT = CANVAS_HEIGHT;
        this.CANVAS_WIDTH = CANVAS_WIDTH;
    }

    public OverlayView(Context context, int backgroundColor, int textColor, int CANVAS_HEIGHT, int CANVAS_WIDTH) {
        super(context);
        init(context);
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.CANVAS_HEIGHT = CANVAS_HEIGHT;
        this.CANVAS_WIDTH = CANVAS_WIDTH;
    }

    private void init(Context context) {
        mContext = context;
        mPackageInfo = Utils.getVersionInfo(mContext);
    }


    public void addToWindowManager() throws OutOfBoundsException {
        WindowManager.LayoutParams windowLayoutParams = new WindowManager.LayoutParams(
                CANVAS_WIDTH,
                CANVAS_HEIGHT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        windowLayoutParams.gravity = Gravity.BOTTOM | Gravity.END;

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setBackgroundColor(backgroundColor);
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(mLinearLayout, windowLayoutParams);

        TextView textView = new TextView(mContext);
        textView.setTextColor(textColor);
        textView.setText(
                String.format("Name: %s \n Code: %s",
                        mPackageInfo.versionName,
                        mPackageInfo.versionCode)
        );
        /*
        Here is checking. if sum of diff's of both height and width is more than x(e.g. 100, 150, 200..) than set
        text at some size. There are some dangerous and annoying else/if statements - could be improved(?)
         */
        int sumDiff = this.CANVAS_HEIGHT - Constants.DEFAULT_CANVAS_HEIGHT + (this.CANVAS_WIDTH - Constants.DEFAULT_CANVAS_WIDTH);
        if(sumDiff >= 50 && sumDiff < 100) textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)getResources().getDimension(R.dimen.light)); //test it
        else if(sumDiff >= 100 && sumDiff < 150) textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)getResources().getDimension(R.dimen.medium));
        else if(sumDiff >= 150 && sumDiff < 200) textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)getResources().getDimension(R.dimen.a_bit_large));
        else if(sumDiff == 200) textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)getResources().getDimension(R.dimen.large));
        else if(sumDiff > 200) {
            throw new OutOfBoundsException("Cannot increase height/width any more!");
        }
       /* float currentTextSize = textView.getTextSize();
        System.out.println("current text size is " + currentTextSize); */
        mLinearLayout.addView(textView);
    }

    /**
     * Removes the view from window manager.
     */
    public void destroy() {
        mWindowManager.removeView(mLinearLayout);
    }
}
