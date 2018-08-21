package get.and.set.alarm.AlarmsActivity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 6/12/2018.
 */

public class AlarmsPresenterImpl implements AlarmsPresenter, AlarmsInteractor.OnAlarmsRetrieved {

    private AlarmsActivity alarmsActivity;
    private AlarmsInteractor alarmsInteractor;
    private AlarmModel alarmModel;
    private ServicesManager servicesManager;

    public AlarmsPresenterImpl(ServicesManager servicesManager, AlarmModel alarmModel, AlarmsActivity alarmsActivity, AlarmsInteractor alarmsInteractor){
        this.servicesManager = servicesManager;
        this.alarmModel = alarmModel;
        this.alarmsActivity = alarmsActivity;
        this.alarmsInteractor = alarmsInteractor;
    }

    @Override
    public void getAlarms() {
        alarmsInteractor.retrieveAlarms(this);
    }

    @Override
    public void deleteAlarm(Alarm alarm) {
        alarmsInteractor.deleteAlarm(alarm, this, alarmModel);
    }

    @Override
    public void askPermissions(String[] requestPermissions) {
        requestPermissions = checkPermissions(requestPermissions);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1){
            if(requestPermissions.length > 0){
                ActivityCompat.requestPermissions(alarmsActivity.getActivity(), requestPermissions, alarmsActivity.PERMISSIONs_REQUEST_CODE);
            }
        }
    }

    private String[] checkPermissions(String[] requestPermissions){
        int[] permissionsRequestsResult = new int[requestPermissions.length];
        for(int i = 0; i<requestPermissions.length; i++){
            permissionsRequestsResult[i] = ContextCompat.checkSelfPermission(alarmsActivity.getActivity(), requestPermissions[i]);
        }
        ArrayList<String> output = new ArrayList<>();
        for(int i = 0; i<requestPermissions.length; i++){
            if (permissionsRequestsResult[i] != PackageManager.PERMISSION_GRANTED){
                output.add(requestPermissions[i]);
            }
        }
        return output.toArray(new String[output.size()]);
    }

    @Override
    public void onSuccess(ArrayList<Alarm> alarms) {
        alarmsActivity.setAlarms(new AlarmsListAdapter(servicesManager,alarms, this));
    }

    @Override
    public void onFail(String errorMessage) {
        alarmsActivity.showMessage(errorMessage);
    }

}
