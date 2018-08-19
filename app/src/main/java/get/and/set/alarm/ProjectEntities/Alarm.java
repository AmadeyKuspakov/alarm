package get.and.set.alarm.ProjectEntities;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Amadey on 6/12/2018.
 */

public class Alarm implements Parcelable{

    private int id;
    private long time;
    private String days;
    private String phrase;
    private boolean onOff;
    private String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sun", "Sat"};
    public static final Parcelable.Creator<Alarm> CREATOR = new Parcelable.Creator<Alarm>(){
        @Override
        public Alarm createFromParcel(Parcel parcel) {
            return new Alarm(parcel);
        }

        @Override
        public Alarm[] newArray(int i) {
            return new Alarm[i];
        }
    };

    private Alarm(Parcel parcel){
        this.id = parcel.readInt();
        this.time = parcel.readLong();
        this.days = parcel.readString();
        this.phrase = parcel.readString();
        this.onOff = parcel.readByte() != 0;
    }

    public Alarm(int id, long time, String days, String phrase, boolean onOff){
        this.id = id;
        this.time = time;
        this.days = days;
        this.phrase = phrase;
        this.onOff = onOff;
    }

    public int getId() {
        return id;
    }

    public long getTimeInMillis() {
        return time;
    }

    public String getDisplayTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Log.e("Point_1","Calendar time is " + calendar.getTime());
        return make2digitNumber(calendar.getTime().getHours()) + ":" + make2digitNumber(calendar.getTime().getMinutes());
    }

    private String make2digitNumber(int number){
        if (number == 0 ) {
            return "00";
        }else if (number > 0 && number < 10) {
            return "0" + String.valueOf(number);
        }else {
            return String.valueOf(number);
        }
    }

    public String getDays() {
        return days;
    }

    public String getDisplayDays(){
        String[] dayNumbers = days.split(",");
        String displayDays = "";
        for(int i = 0; i<dayNumbers.length; i++){
            displayDays = displayDays.concat(weekdays[Integer.parseInt(dayNumbers[i])] + ",");
        }
        return displayDays.substring(0, displayDays.length() - 1);
    }

    public String getPhrase() {
        return phrase;
    }

    public boolean isOnOff(){
        return onOff;
    }

    @Override
    public String toString() {
        return String.format("ID is %1$s, Time is %2$s, Days are %3$s, Phrase is %4$s, IsOn %5$s", id, time, days, phrase, onOff);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeLong(time);
        parcel.writeString(days);
        parcel.writeString(phrase);
        parcel.writeByte((byte) (onOff ? 1 : 0));
    }

}
