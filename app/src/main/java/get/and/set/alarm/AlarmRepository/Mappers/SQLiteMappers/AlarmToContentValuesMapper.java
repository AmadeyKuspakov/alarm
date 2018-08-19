package get.and.set.alarm.AlarmRepository.Mappers.SQLiteMappers;

import android.content.ContentValues;

import get.and.set.alarm.AlarmRepository.Mappers.Mapper;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/29/2018.
 */

public class AlarmToContentValuesMapper implements Mapper<Alarm, ContentValues> {

    @Override
    public ContentValues map(Alarm from) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, from.getId());
        contentValues.put(KEY_TIME, from.getTimeInMillis());
        contentValues.put(KEY_DAYS, from.getDays());
        contentValues.put(KEY_PHRASE, from.getPhrase());
        contentValues.put(KEY_ONOFF, from.isOnOff());
        return contentValues;
    }

}
