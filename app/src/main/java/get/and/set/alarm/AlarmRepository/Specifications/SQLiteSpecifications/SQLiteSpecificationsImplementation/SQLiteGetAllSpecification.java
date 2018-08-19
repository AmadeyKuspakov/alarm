package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation;

import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/30/2018.
 */

public class SQLiteGetAllSpecification implements SQLiteSpecification<Alarm> {

    @Override
    public String getTable() {
        return TABLE_ALARMS;
    }

    @Override
    public String toExecStatement() {
        return String.format("SELECT * FROM %1$s", TABLE_ALARMS);
    }

    @Override
    public Alarm getObject() {
        return null;
    }

}
