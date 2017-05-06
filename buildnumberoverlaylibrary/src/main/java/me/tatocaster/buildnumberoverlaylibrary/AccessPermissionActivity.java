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

import java.util.HashMap;

import me.tatocaster.buildnumberoverlaylibrary.utils.Utils;

/**
 * Created by tatocaster on 1/28/17.
 */

public class AccessPermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 9991;

    private static HashMap customsPassingToService = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        customsPassingToService = (HashMap)intent.getExtras().getSerializable("customizations");
        if (isSystemAlertPermissionGranted(this))
            startOverlayService();
        else
            requestSystemAlertPermission(this, null, PERMISSION_REQUEST_CODE);
    }

    /**
     * start the service
     */
    private void startOverlayService() {

        if (!Utils.isOverlayingServiceIsRunning(this, OverlayService.class)) {
            Intent intent = new Intent(AccessPermissionActivity.this, OverlayService.class);
            intent.putExtra("customizations", customsPassingToService);
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
            startOverlayService();

        super.onActivityResult(requestCode, resultCode, data);
    }
}
