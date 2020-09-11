package com.diusframi.android.service.stats;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null){
            context.startService(new Intent(context,ServiceStats.class));
        }
    }
}
