package get.and.set.alarm.AlarmsActivity;

import android.Manifest;

import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/12/2018.
 */

public interface AlarmsPresenter {

    public void getAlarms();

    public void deleteAlarm(Alarm alarm);

    public void askPermissions(String[] requestPermissions);

}
