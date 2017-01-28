package me.tatocaster.buildnumberoverlaylibrary;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import me.tatocaster.buildnumberoverlaylibrary.NumberOverlayView.OverlayView;

/**
 * Created by tatocaster on 1/28/17.
 */

public class OverlayService extends Service {
    private static final String TAG = "OverlayService";
    private static final int FOREGROUND_ID = 9998;
    private OverlayView mOverlayView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mOverlayView = new OverlayView(NumberOverlay.getApplicationContext());
        mOverlayView.addToWindowManager();

        // this needs to be here, because without the startForeground(), our view will not retain always
/*        startForeground(FOREGROUND_ID, createNotification());
        return START_STICKY;*/
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mOverlayView.destroy();
        super.onDestroy();
    }


    private Notification createNotification() {

        /*Intent intent = new Intent(this, AccessPermissionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);*/

        return new NotificationCompat.Builder(this)
                .setContentTitle("Build Number Overlay")
                .build();
    }
}
