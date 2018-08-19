package get.and.set.alarm.AddAlarmActivity;

import android.app.Activity;

import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 7/1/2018.
 */

public interface AddAlarm {

    public void showMessage(String textMessage);

    public void finishAddition();

    public Activity getActivity();

}
