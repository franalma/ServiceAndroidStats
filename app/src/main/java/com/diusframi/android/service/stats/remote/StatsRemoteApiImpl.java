package com.diusframi.android.service.stats.remote;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.diusframi.android.androidstatserviceapi.StatsRemoteApi;
import com.diusframi.android.service.stats.ServiceGlobalInfo;
import com.diusframi.android.stats.Log;

public class StatsRemoteApiImpl extends StatsRemoteApi.Stub {

    @Override
    public String getLogInfo()  {
        Log.init(ServiceGlobalInfo.getInstance().getContext());
        return Log.getInstance().getLogFullInfo();
    }

    @Override
    public boolean clearStatLogs() {
        Log.init(ServiceGlobalInfo.getInstance().getContext());
        return Log.getInstance().clearLogs();

    }

    @Override
    public void getPaxSdkLogs() throws RemoteException {
        Context context = ServiceGlobalInfo.getInstance().getContext();
        if (context != null){
            context.sendBroadcast(new Intent("com.disframi.pax.sdk.wrapper.getlogs"));
        }
    }
}
