package get.and.set.alarm.AlarmsActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.util.Log;

import java.util.ArrayList;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.AlarmReceiver;
import get.and.set.alarm.AlarmRepository.AlarmRepository;
import get.and.set.alarm.AlarmRepository.SQLite.SQLiteAlarmRepository;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteDeleteByIdSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteGetAllSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 6/13/2018.
 */

public class AlarmsInteractorImpl implements AlarmsInteractor {

    private AlarmRepository alarmRepository;
    private final String alarmsRetrievalError = "Unfortunately, alarms could not be retrieved.";
    private final String alarmDeletionError = "Something went wrong during deletion.";

    public AlarmsInteractorImpl(){
        alarmRepository = new SQLiteAlarmRepository();
    }

    @Override
    public void retrieveAlarms(OnAlarmsRetrieved onAlarmsRetrieved) {
        try {
            onAlarmsRetrieved.onSuccess(alarmRepository.get(new SQLiteGetAllSpecification()));
        }catch (Exception e){
            Log.e("Point_1", "AlarmsInteractorImpl_Exception " + e.getMessage());
            e.printStackTrace();
            onAlarmsRetrieved.onFail(alarmsRetrievalError);
        }
    }

    @Override
    public void deleteAlarm(Alarm alarm, OnAlarmsRetrieved onAlarmsRetrieved, AlarmModel alarmModel) {
        alarmModel.cancelAlarm(alarm);
        if(!alarmRepository.delete(new SQLiteDeleteByIdSpecification(alarm))){
            onAlarmsRetrieved.onFail(alarmDeletionError);
        };
    }

}
