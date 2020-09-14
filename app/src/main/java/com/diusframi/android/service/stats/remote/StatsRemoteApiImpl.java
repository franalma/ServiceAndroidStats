package com.diusframi.android.service.stats.remote;

import android.os.RemoteException;
import com.diusframi.android.androidstatserviceapi.StatsRemoteApi;
import com.diusframi.android.service.stats.ServiceGlobalInfo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StatsRemoteApiImpl extends StatsRemoteApi.Stub {

    @Override
    public String getLogInfo() throws RemoteException {

        JSONArray jac = new JSONArray();
        try{
            File dir = ServiceGlobalInfo.getInstance().getContext().getFilesDir();
            BufferedReader reader = new BufferedReader(new FileReader(dir+"/"+"android_service_stats.json"));
            String line;
            while((line = reader.readLine()) != null){
                line = line.replaceAll("\\\\","");
                JSONObject joc = new JSONObject(line);
                jac.put(joc);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return jac.toString();
    }
}
