package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation;

import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/29/2018.
 */

public class SQLiteCreationSpecification implements SQLiteSpecification<Alarm> {

    @Override
    public String getTable() {
        return TABLE_ALARMS;
    }

    @Override
    public String toExecStatement() {
        return String.format("CREATE TABLE IF NOT EXISTS %1$s(%2$s INTEGER PRIMARY KEY AUTOINCREMENT, %3$s TEXT, %4$s TEXT, %5$s TEXT, %6$s BIT)", TABLE_ALARMS, KEY_ID, KEY_TIME, KEY_DAYS, KEY_PHRASE, KEY_ONOFF);
    }

    @Override
    public Alarm getObject() {
        return null;
    }

}
