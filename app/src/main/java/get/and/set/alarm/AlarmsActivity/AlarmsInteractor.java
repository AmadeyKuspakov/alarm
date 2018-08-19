package get.and.set.alarm.AlarmsActivity;

import java.util.ArrayList;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 6/13/2018.
 */

public interface AlarmsInteractor {

    public interface OnAlarmsRetrieved{

        public void onSuccess(ArrayList<Alarm> alarms);

        public void onFail(String errorMessage);

    }

    public void retrieveAlarms(OnAlarmsRetrieved onAlarmsRetrieved);

    public void deleteAlarm(Alarm alarm, OnAlarmsRetrieved onAlarmsRetrieved, AlarmModel alarmModel);

}
