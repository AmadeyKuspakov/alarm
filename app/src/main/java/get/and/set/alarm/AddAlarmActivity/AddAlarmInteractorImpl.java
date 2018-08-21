package get.and.set.alarm.AddAlarmActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.AlarmReceiver;
import get.and.set.alarm.AlarmRepository.AlarmRepository;
import get.and.set.alarm.AlarmRepository.SQLite.SQLiteAlarmRepository;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteInsertionSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 7/1/2018.
 */

public class AddAlarmInteractorImpl implements AddAlarmInteractor {

    private AlarmRepository alarmRepository;

    public AddAlarmInteractorImpl(AlarmRepository alarmRepository){
        this.alarmRepository = alarmRepository;
    }

    @Override
    public void saveAlarm(AlarmModel alarmModel, Alarm alarm, AddAlarmInteractor.OnAlarmAdded onAlarmAdded) {
        try{
            if(alarmRepository.insert(new SQLiteInsertionSpecification(alarm))) {
                alarmModel.setAlarm(alarm);
                onAlarmAdded.onSuccess();
            }else{
                onAlarmAdded.onFail("Alarm was not added");
            }
        }catch (Exception e){
            Log.e("Point_1", "AddAlarmInteractorImpl_Exception " + e.getMessage());
            e.printStackTrace();
            onAlarmAdded.onFail("Sorry, something went wrong during creation of a new alarm");
        }
    }
}
