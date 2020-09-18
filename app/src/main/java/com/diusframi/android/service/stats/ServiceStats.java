package com.diusframi.android.service.stats;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.diusframi.android.service.stats.remote.StatsRemoteApiImpl;


public class ServiceStats extends Service {
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        StatsManager.getInstance().init(getBaseContext());
        return new StatsRemoteApiImpl().asBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        StatsManager.getInstance().onUnBind(getBaseContext());
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        StatsManager.getInstance().init(getBaseContext());
        return super.onStartCommand(intent, flags, startId);
    }
    


}
