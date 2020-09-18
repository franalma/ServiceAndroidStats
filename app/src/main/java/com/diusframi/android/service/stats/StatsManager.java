package com.diusframi.android.service.stats;

import android.content.Context;

import com.diusframi.android.stats.BatteryStats;
import com.diusframi.android.stats.Log;
import com.diusframi.android.stats.WiifStats;

public class StatsManager {
    
    private static StatsManager instance;
    private boolean isInit = false;
    
    private StatsManager(){}

    public void init(Context context){
        ServiceGlobalInfo.getInstance().setContext(context);
        Log.init(context);
        BatteryStats.getInstance().init(context);
        WiifStats.getInstance().init(context);
    }

    public void onUnBind(Context context){
        BatteryStats.getInstance().stop(context);
        WiifStats.getInstance().stop(context);
    }
    
    public static StatsManager getInstance(){
        if (instance == null){
            instance = new StatsManager();
        }
        return instance; 
    }
}
