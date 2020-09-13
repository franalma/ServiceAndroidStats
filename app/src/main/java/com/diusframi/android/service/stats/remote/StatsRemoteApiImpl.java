package com.diusframi.android.service.stats.remote;

import android.os.RemoteException;
import com.diusframi.android.androidstatserviceapi.StatsRemoteApi;
import com.diusframi.android.service.stats.ServiceGlobalInfo;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StatsRemoteApiImpl extends StatsRemoteApi.Stub {

    @Override
    public String getLogInfo() throws RemoteException {
        JSONObject joc = new JSONObject();
        try{
            File dir = ServiceGlobalInfo.getInstance().getContext().getFilesDir();
            BufferedReader reader = new BufferedReader(new FileReader(dir+"/"+"android_service_stats.json"));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return joc.toString();
    }
}
