package get.and.set.alarm.AddAlarmActivity;

import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 7/1/2018.
 */

public interface AddAlarmPresenter {

    public void addAlarm(AlarmModel alarmModel, Alarm alarm);

    public long convertToMillis(TimePicker timePicker, Calendar calendar);

    public void weekdayPressed(int id);

    public String getDays();

    public void setInitialPressStatus(Button[] buttons, String days);

    public void setTime(TimePicker timePicker, String time);

}
