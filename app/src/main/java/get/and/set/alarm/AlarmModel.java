package get.and.set.alarm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 8/19/2018.
 */

public class AlarmModel {

    private Context context;

    public AlarmModel(Context context){
        this.context = context;
    }

    public PendingIntent getPendingIntent(Intent intent, Alarm alarm){
        return PendingIntent.getBroadcast(context, alarm.getId(), intent, PendingIntent.FLAG_ONE_SHOT);
    }

    public void setAlarm(Alarm alarm){
//        ServicesManager servicesManager = ServicesManager.getInstance();
//        AlarmManager alarmManager = servicesManager.getAlarmServiceManager();
//        Intent intent = servicesManager.getIntent(AlarmReceiver.class);
//        intent.putExtra("phrase", alarm.getPhrase());
//        PendingIntent pendingIntent = servicesManager.getPendingIntent(intent, alarm);
//        Log.e("Point_1", "Time for the alarm is " + alarm.getDisplayTime());
//        if (Build.VERSION.SDK_INT > 18) {
//            alarmManager.setExact(AlarmManager.RTC, alarm.getTimeInMillis(), pendingIntent);
//        } else {
//            alarmManager.set(AlarmManager.RTC, alarm.getTimeInMillis(), pendingIntent);
//        }
    }

    public void cancelAlarm(Alarm alarm){
//        ServicesManager servicesManager = ServicesManager.getInstance();
//        AlarmManager alarmManager = servicesManager.getAlarmServiceManager();
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(servicesManager.getContext(), alarm.getId(), servicesManager.getIntent(AlarmReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
//        alarmManager.cancel(pendingIntent);
    }

}
