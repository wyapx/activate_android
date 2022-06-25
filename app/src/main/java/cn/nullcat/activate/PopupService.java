package cn.nullcat.activate;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class PopupService extends IntentService {

    private WindowManager windowManager;
    private View currentView;
    WindowManager.LayoutParams params;

    public PopupService() {
        super("PopupService");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        if (currentView != null) {
            windowManager.removeView(currentView);
            return;
        }
        Log.i("aa", "Service started");

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        currentView = inflater.inflate(R.layout.activity_popup, null);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.BOTTOM | Gravity.END;
        params.x = 50;
        params.y = 50;

        windowManager.addView(currentView, params);
        // setContentView(R.layout.activity_popup);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Log.i("aas", "destory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("aas", "starting");
    }
}