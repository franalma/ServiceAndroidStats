package com.diusframi.android.service.stats.remote;

import android.os.RemoteException;
import com.diusframi.android.androidstatserviceapi.StatsRemoteApi;
import com.diusframi.android.service.stats.ServiceGlobalInfo;
import com.diusframi.android.stats.Log;

public class StatsRemoteApiImpl extends StatsRemoteApi.Stub {

    @Override
    public String getLogInfo() throws RemoteException {
        Log.init(ServiceGlobalInfo.getInstance().getContext());
        return Log.getInstance().getLogFullInfo();
    }

    @Override
    public boolean clearStatLogs() throws RemoteException {
        Log.init(ServiceGlobalInfo.getInstance().getContext());
        return Log.getInstance().clearLogs();

    }
}
