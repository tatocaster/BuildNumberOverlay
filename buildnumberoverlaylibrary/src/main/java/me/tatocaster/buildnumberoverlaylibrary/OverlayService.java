package me.tatocaster.buildnumberoverlaylibrary;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
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
	private static final String PROPERTIES = "Properties";
	private OverlayView mOverlayView;
	private OverlayView.Properties properties;
	
	@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		getPropertiesFromExtra(intent);
	    
        mOverlayView = new OverlayView(NumberOverlay.getApplicationContext());
        mOverlayView.addToWindowManager(properties);
	    
	    

        // this needs to be here, because without the startForeground(), our view will not retain always
/*        startForeground(FOREGROUND_ID, createNotification());
        return START_STICKY;*/
        return START_NOT_STICKY;
    }
	
	private void getPropertiesFromExtra(Intent intent) {
		if (intent == null){
			return;
		}
		
		properties = (OverlayView.Properties) intent.getSerializableExtra(PROPERTIES);
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
	
	public static Intent getCallingIntent(Context context, OverlayView.Properties properties) {
		Intent intent = new Intent(context, OverlayService.class);
		intent.putExtra(PROPERTIES, properties);
		return intent;
	}
}
