package get.and.set.alarm.AlarmRepository.Mappers.SQLiteMappers;

import android.database.Cursor;

import get.and.set.alarm.AlarmRepository.Mappers.Mapper;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/30/2018.
 */

public class CursorToAlarmMapper implements Mapper<Cursor, Alarm> {
    @Override
    public Alarm map(Cursor from) {
        return new Alarm(from.getInt(0), Long.parseLong(from.getString(1)), from.getString(2), from.getString(3), from.getInt(4) > 0);
    }
}
