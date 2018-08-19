package get.and.set.alarm;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;

import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/12/2018.
 */

public class ServicesManager {

    private Context context;

    public ServicesManager(Context context) {
        context = context;
    }

    public Context getContext(){
        return context;
    }

    public AlarmManager getAlarmServiceManager(){
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public LayoutInflater getLayoutInflater(){
        return LayoutInflater.from(context);
    }

    public Intent getIntent(Class destination){
        return new Intent(context, destination);
    }
    public WindowManager getWindowManager(){
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public Vibrator getVibrator(){
        return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }
}
