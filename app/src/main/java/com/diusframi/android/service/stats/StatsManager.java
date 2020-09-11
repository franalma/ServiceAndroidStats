package com.diusframi.android.service.stats;

import android.content.Context;

import com.diusframi.android.stats.BatteryStats;
import com.diusframi.android.stats.Log;
import com.diusframi.android.stats.WiifStats;

public class StatsManager {
    
    private static StatsManager instance;
    
    private StatsManager(){}

    public void init(Context context){
        Log.init(context);
        BatteryStats.getInstance().init(context);
        WiifStats.getInstance().init(context);
    }
    
    public static StatsManager getInstance(){
        if (instance == null){
            instance = new StatsManager();
        }
        return instance; 
    }
}
