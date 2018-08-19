package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation;

import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/29/2018.
 */

public class SQLiteInsertionSpecification implements SQLiteSpecification<Alarm> {

    private Alarm alarm;

    public SQLiteInsertionSpecification(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public String getTable() {
        return TABLE_ALARMS;
    }

    @Override
    public String toExecStatement() {
        return null;
    }

    @Override
    public Alarm getObject() {
        return alarm;
    }

}
