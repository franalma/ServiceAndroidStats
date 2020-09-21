package com.diusframi.android.androidstatserviceapi;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import org.json.JSONArray;
import org.json.JSONException;

public class DandroidStats {

    private static DandroidStats instance;
    private DandroidStatsDelegate connectionDelegate;
    private StatsRemoteApi remoteApi;
    private DandroidStatsDelegate sdkDelegate;
    private BroadcastReceiver receiver;

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

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (sdkDelegate != null){
                    String res = intent.getStringExtra("result");
                    try {
                        JSONArray jac = new JSONArray(res);
                        sdkDelegate.onPaxLogsReceived(jac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    sdkDelegate = null;
                }
            }
        };
        IntentFilter filter = new IntentFilter("com.disframi.pax.sdk.wrapper.getlogs.response");
        context.registerReceiver(receiver, filter);
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

    public void getPaxSdkLogs(DandroidStatsDelegate delegate){
        try{
            this.sdkDelegate = delegate;
            remoteApi.getPaxSdkLogs();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public interface DandroidStatsDelegate{
        void onInit(boolean status);
        void onConnectionLost();
        void onPaxLogsReceived(JSONArray data);
    }

    public static DandroidStats getInstance(){
        if (instance == null){
            instance = new DandroidStats();
        }
        return instance; 
    }
}
