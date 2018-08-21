package get.and.set.alarm.AddAlarmActivity;

import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 7/1/2018.
 */

public class AddAlarmPresenterImpl implements AddAlarmPresenter, AddAlarmInteractor.OnAlarmAdded {

    private AddAlarm addAlarm;
    private AddAlarmInteractor addAlarmInteractor;
    private boolean[] pressedOrNot;

    public AddAlarmPresenterImpl(AddAlarm addAlarm, AddAlarmInteractor addAlarmInteractor) {
        this.addAlarm = addAlarm;
        this.addAlarmInteractor = addAlarmInteractor;
        pressedOrNot = new boolean[7];
    }

    @Override
    public void addAlarm(AlarmModel alarmModel, Alarm alarm) {
        addAlarmInteractor.saveAlarm(alarmModel, alarm, this);
    }

    @Override
    public long convertToMillis(TimePicker timePicker, Calendar calendar) {
        if (Build.VERSION.SDK_INT > 22) {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        }
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    @Override
    public void weekdayPressed(int day) {
        day = day - 1;
        if (!pressedOrNot[day]) {
            pressedOrNot[day] = true;
        } else {
            pressedOrNot[day] = false;
        }
    }

    @Override
    public String getDays() {
        String numbersOfDays = "";
        for (int i = 0; i < 7; i++) {
            // if in boolean array of days ith element is true then it is the day when alarm should be working
            if (pressedOrNot[i]) {
                numbersOfDays += String.valueOf(i) + ",";
            }
        }
        return numbersOfDays.substring(0, numbersOfDays.length() - 1);
    }

    @Override
    public void setInitialPressStatus(Button[] buttons, String days) {
        String[] dayNumbers = days.split(",");
        for (int i = 0; i<dayNumbers.length; i++) {
            buttons[Integer.parseInt(dayNumbers[i])].setEnabled(true);
            pressedOrNot[Integer.parseInt(dayNumbers[i])] = true;
        }
    }

    @Override
    public void setTime(TimePicker timePicker, String time) {
        String[] hoursAndMinutes = time.split(":");
        if (Build.VERSION.SDK_INT > 22) {
            timePicker.setHour(Integer.parseInt(hoursAndMinutes[0]));
            timePicker.setMinute(Integer.parseInt(hoursAndMinutes[1]));
        }else {
            timePicker.setCurrentHour(Integer.parseInt(hoursAndMinutes[0]));
            timePicker.setCurrentMinute(Integer.parseInt(hoursAndMinutes[1]));
        }
    }

    @Override
    public void onSuccess() {
        addAlarm.finishAddition();
    }

    @Override
    public void onFail(String errorMessage) {
        addAlarm.showMessage(errorMessage);
    }

}
