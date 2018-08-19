package get.and.set.alarm.AddAlarmActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

import get.and.set.alarm.AlarmModel;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.R;
import get.and.set.alarm.ServicesManager;

public class AddAlarmImpl extends Activity implements AddAlarm, View.OnClickListener{

    private AddAlarmPresenter presenter;
    private int id;
    private TimePicker timePicker;
    private EditText editText;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        presenter = new AddAlarmPresenterImpl(this);
        editText = findViewById(R.id.phraseBox);
        timePicker = findViewById(R.id.timePicker);
        findViewById(R.id.Monday).setOnClickListener(this);
        findViewById(R.id.Tuesday).setOnClickListener(this);
        findViewById(R.id.Wednessday).setOnClickListener(this);
        findViewById(R.id.Thursday).setOnClickListener(this);
        findViewById(R.id.Friday).setOnClickListener(this);
        findViewById(R.id.Sunday).setOnClickListener(this);
        findViewById(R.id.Saturday).setOnClickListener(this);
        findViewById(R.id.NewAlarm).setOnClickListener(this);
        String intention = getIntent().getStringExtra("intention");
        switch (intention){
            case "new":
                id = getIntent().getIntExtra("id", 0);
                break;
            case "edit":
                Alarm alarm = getIntent().getParcelableExtra("alarm");
                id = alarm.getId();
                editText.setText(alarm.getPhrase());
                presenter.setTime(timePicker, alarm.getDisplayTime());
                presenter.setInitialPressStatus(new int[]{R.id.Monday, R.id.Tuesday, R.id.Wednessday, R.id.Thursday, R.id.Friday, R.id.Sunday, R.id.Saturday}, alarm.getDays());
                break;
        }
    }

    @Override
    public void showMessage(String textMessage) {
        Toast.makeText(getBaseContext(), textMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishAddition(){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Monday:
                presenter.weekdayPressed(1);
                break;
            case R.id.Tuesday:
                presenter.weekdayPressed(2);
                break;
            case R.id.Wednessday:
                presenter.weekdayPressed(3);
                break;
            case R.id.Thursday:
                presenter.weekdayPressed(4);
                break;
            case R.id.Friday:
                presenter.weekdayPressed(5);
                break;
            case R.id.Sunday:
                presenter.weekdayPressed(6);
                break;
            case R.id.Saturday:
                presenter.weekdayPressed(7);
                break;
            case R.id.NewAlarm:
                presenter.addAlarm(new AlarmModel(AddAlarmImpl.this.getBaseContext()), new Alarm(id + 1, presenter.convertToMillis(timePicker), presenter.getDays(), editText.getText().toString(), true));
        }
    }
}
