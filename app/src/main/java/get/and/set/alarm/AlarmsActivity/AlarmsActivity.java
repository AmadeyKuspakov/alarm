package get.and.set.alarm.AlarmsActivity;

import android.Manifest;
import android.app.Activity;

/**
 * Created by Amadey on 6/12/2018.
 */

public interface AlarmsActivity {

    public static final int PERMISSIONs_REQUEST_CODE = 7;
    public static final String[] permissions = {Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.VIBRATE};

    public void setAlarms(AlarmsListAdapter listAdapter);

    public void showMessage(String textMessage);

    public Activity getActivity();

}
