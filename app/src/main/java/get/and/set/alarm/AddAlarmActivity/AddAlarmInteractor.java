package get.and.set.alarm.AddAlarmActivity;

import android.widget.TimePicker;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 7/1/2018.
 */

public interface AddAlarmInteractor {

    public interface OnAlarmAdded{

        public void onSuccess();

        public void onFail(String errorMessage);

    }

    public void saveAlarm(AlarmModel alarmModel, Alarm alarm, OnAlarmAdded onAlarmAdded);

}
