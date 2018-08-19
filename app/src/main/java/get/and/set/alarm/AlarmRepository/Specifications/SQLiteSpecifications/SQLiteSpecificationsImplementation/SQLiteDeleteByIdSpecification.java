package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation;

import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/30/2018.
 */

public class SQLiteDeleteByIdSpecification implements SQLiteSpecification<Alarm> {

    private Alarm alarm;

    public SQLiteDeleteByIdSpecification(Alarm alarm){
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
