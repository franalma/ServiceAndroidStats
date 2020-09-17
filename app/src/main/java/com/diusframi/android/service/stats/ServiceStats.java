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
        ServiceGlobalInfo.getInstance().setContext(ServiceStats.this);
        return new StatsRemoteApiImpl().asBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        StatsManager.getInstance().init(getBaseContext());
        return super.onStartCommand(intent, flags, startId);
    }
    


}
