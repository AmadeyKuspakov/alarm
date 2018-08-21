package get.and.set.alarm.AlarmsActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import get.and.set.alarm.AddAlarmActivity.AddAlarmImpl;
import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.AlarmRepository.SQLite.SQLiteAlarmRepository;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.R;
import get.and.set.alarm.ServicesManager;

public class AlarmsActivityImpl extends Activity implements AlarmsActivity, View.OnClickListener{

    private ListView listView;
    private AlarmsPresenter presenter;
    private int id;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarms);
        (findViewById(R.id.add_alarm)).setOnClickListener(this);
        listView = findViewById(R.id.alarms);
        presenter = new AlarmsPresenterImpl(new ServicesManager(this.getBaseContext()), new AlarmModel(this.getBaseContext()),this, new AlarmsInteractorImpl(new SQLiteAlarmRepository(this.getBaseContext())));
        presenter.askPermissions(permissions);
        presenter.getAlarms();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSIONs_REQUEST_CODE:
                ArrayList<String> requestPermissions = new ArrayList<>();
                for(int i = 0; i<grantResults.length; i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                        requestPermissions.add(permissions[i]);
                    }
                }
                if(requestPermissions.size()>0){
                    presenter.askPermissions(requestPermissions.toArray(new String[requestPermissions.size()]));
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.add_alarm):
                Intent intent = new Intent(AlarmsActivityImpl.this, AddAlarmImpl.class);
                intent.putExtra("intention", "new");
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setAlarms(AlarmsListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
        id = (listAdapter.getAlarms().get(listAdapter.getCount() - 1)).getId();
    }

    @Override
    public void showMessage(String textMessage) {
        Toast.makeText(this.getBaseContext(), textMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAlarms();
    }
}
