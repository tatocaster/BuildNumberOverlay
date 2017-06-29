package me.tatocaster.buildnumberoverlaylibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView.OverlayView;
import me.tatocaster.buildnumberoverlaylibrary.utils.Utils;

/**
 * Created by tatocaster on 1/28/17.
 */

public class AccessPermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 9991;
	private static final String PROPERTIES = "Properties";
	
	private OverlayView.Properties properties;
	
	
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
	    getPropertiesFromExtra();
	    
        if (isSystemAlertPermissionGranted(this))
            startOverlayService(properties);
        else
            requestSystemAlertPermission(this, null, PERMISSION_REQUEST_CODE);
    }
	
	private void getPropertiesFromExtra() {
		if (getIntent() == null){
			return;
		}
		
		properties = (OverlayView.Properties) getIntent().getSerializableExtra(PROPERTIES);
	}
	
	/**
     * start the service
	 * @param properties
	 */
    private void startOverlayService(OverlayView.Properties properties) {
        if (!Utils.isOverlayingServiceIsRunning(this, OverlayService.class)) {
	        Intent intent = OverlayService.getCallingIntent(this, properties);
	        startService(intent);
        }
        finish();
    }

    /**
     * @param fragment
     * @param requestCode
     */
    public void requestSystemAlertPermission(Activity context, Fragment fragment, int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        final String packageName = context == null ? fragment.getActivity().getPackageName() : context.getPackageName();
        final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName));
        if (fragment != null)
            fragment.startActivityForResult(intent, requestCode);
        else
            context.startActivityForResult(intent, requestCode);
    }

    /**
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isSystemAlertPermissionGranted(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERMISSION_REQUEST_CODE && isSystemAlertPermissionGranted(NumberOverlay.getApplicationContext()))
            startOverlayService(properties);

        super.onActivityResult(requestCode, resultCode, data);
    }
    
    public static Intent getCallingIntent(Context context, OverlayView.Properties properties){
	    Intent intent = new Intent(context, AccessPermissionActivity.class);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.putExtra(PROPERTIES, properties);
	    return intent;
    }
}
