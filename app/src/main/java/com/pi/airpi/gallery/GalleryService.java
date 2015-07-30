package com.pi.airpi.gallery;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

import java.util.List;

/**
 * Created by LC on 2015/7/29.
 */
public class GalleryService extends Service {
    private static GalleryManager GALLERY_MANAGER;

    public static GalleryManager getGalleryManager(Context appContext){
        if(!GalleryService.isServiceRunning(appContext)){
            Intent galleryService = new Intent("pano.gallery.service.action");
            appContext.startService(galleryService);
        }
        if(GalleryService.GALLERY_MANAGER==null){
            GalleryService.GALLERY_MANAGER = new GalleryManager(appContext);
        }
        return GALLERY_MANAGER;
    }

    public GalleryService(){
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       return super.onStartCommand(intent,flags, startId);
    }

    @Override
    public void onDestroy() {
        if (GALLERY_MANAGER != null) {
            try {
               GALLERY_MANAGER.removePanoInfo(0);
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        super.onDestroy();
    }


    public static boolean isServiceRunning(Context context) {
        boolean isRunning = false;

        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (serviceList == null || serviceList.size() == 0) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(GalleryService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

}
