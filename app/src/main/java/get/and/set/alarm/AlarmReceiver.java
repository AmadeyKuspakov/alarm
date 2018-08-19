package get.and.set.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import get.and.set.alarm.FlaotingWindow.FloatingWindowServiceImpl;

/**
 * Created by Amadey on 6/30/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Point_1","This is log from AlarmReceiver.class");
        Intent intent1 = new Intent(context, FloatingWindowServiceImpl.class);
        intent1.putExtra("phrase", intent.getStringExtra("phrase"));
        context.startService(intent1);
    }



}
