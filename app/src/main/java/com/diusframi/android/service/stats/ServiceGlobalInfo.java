package com.diusframi.android.service.stats;

import android.content.Context;

public class ServiceGlobalInfo {
    private static ServiceGlobalInfo instance;
    private Context context;

    private ServiceGlobalInfo(){}

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static ServiceGlobalInfo getInstance(){
        if (instance == null){
            instance = new ServiceGlobalInfo();
        }
        return instance;
    }
}
