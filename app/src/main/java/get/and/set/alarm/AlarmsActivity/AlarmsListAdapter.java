package get.and.set.alarm.AlarmsActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import get.and.set.alarm.AddAlarmActivity.AddAlarmImpl;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.R;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 6/12/2018.
 */

public class AlarmsListAdapter extends BaseAdapter {

    private class ViewHolder{
        private TextView time;
        private TextView days;
        private TextView phrase;
        private Switch onOff;
    }

    private LayoutInflater layoutInflater;
    private ArrayList<Alarm> alarms;
    private AlarmsPresenter presenter;

    public AlarmsListAdapter(ArrayList<Alarm> alarms, AlarmsPresenter presenter){
        layoutInflater = ServicesManager.getInstance().getLayoutInflater();
        this.alarms = alarms;
        this.presenter = presenter;
    }

    public ArrayList<Alarm> getAlarms(){
        return alarms;
    }

    @Override
    public int getCount() {
        return alarms.size();
    }

    @Override
    public Object getItem(int i) {
        return alarms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.single_alarm, null);
            viewHolder = new ViewHolder();
            viewHolder.time = view.findViewById(R.id.time);
            viewHolder.days = view.findViewById(R.id.days);
            viewHolder.phrase = view.findViewById(R.id.phrase);
            viewHolder.onOff = view.findViewById(R.id.onOff);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.time.setText(alarms.get(i).getDisplayTime());
        viewHolder.days.setText(alarms.get(i).getDisplayDays());
        viewHolder.phrase.setText(alarms.get(i).getPhrase());
        viewHolder.onOff.setChecked(alarms.get(i).isOnOff());
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                presenter.deleteAlarm(alarms.get(i));
                notifyDataSetChanged();
                alarms.remove(i);
                return false;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Point_1", "OnItemClickListener " + (i));
                Intent intent = new Intent(ServicesManager.getInstance().getContext(), AddAlarmImpl.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("intention", "edit");
                intent.putExtra("alarm", (Alarm) alarms.get(i));
                ServicesManager.getInstance().getContext().startActivity(intent);
            }
        });
        return view;
    }
}
