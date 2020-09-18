package com.diusframi.android.androidstatserviceapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class DandroidStats {

    private static DandroidStats instance;
    private DandroidStatsDelegate connectionDelegate;
    private StatsRemoteApi remoteApi;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            remoteApi = StatsRemoteApi.Stub.asInterface(iBinder);
            connectionDelegate.onInit(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            connectionDelegate.onConnectionLost();
        }
    };


    private DandroidStats(){}

    public void init(Context context, DandroidStatsDelegate delegate){
        this.connectionDelegate = delegate;
        Intent intent = new Intent("com.diusframi.android.service.stats.ServiceStats");
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public String getLogInfo(){
        String logs = null;
        try{
            logs = remoteApi.getLogInfo();
        }catch (Exception e){
            e.printStackTrace();
        }
        return logs;
    }

    public boolean clearStatLogs(){

        boolean res = false;
        try{
            res = remoteApi.clearStatLogs();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    public interface DandroidStatsDelegate{
        void onInit(boolean status);
        void onConnectionLost();
    }

    public static DandroidStats getInstance(){
        if (instance == null){
            instance = new DandroidStats();
        }
        return instance; 
    }
}
