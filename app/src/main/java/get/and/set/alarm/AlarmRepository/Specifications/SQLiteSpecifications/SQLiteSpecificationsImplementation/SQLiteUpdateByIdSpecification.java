package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation;

import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/30/2018.
 */

public class SQLiteUpdateByIdSpecification implements SQLiteSpecification<Alarm> {

    private Alarm alarm;

    public SQLiteUpdateByIdSpecification(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public String getTable() {
        return TABLE_ALARMS;
    }

    @Override
    public String toExecStatement() {
        return String.format("%1$s = %2$s", KEY_ID, String.valueOf(alarm.getId()));
    }

    @Override
    public Alarm getObject() {
        return alarm;
    }

}
