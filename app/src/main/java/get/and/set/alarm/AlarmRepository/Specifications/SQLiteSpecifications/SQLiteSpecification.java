package get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications;

import get.and.set.alarm.AlarmRepository.SQLite.SQLiteKeys;
import get.and.set.alarm.AlarmRepository.Specifications.Specification;

/**
 * Created by Amadey on 6/29/2018.
 */

public interface SQLiteSpecification<T> extends Specification, SQLiteKeys {

    public String getTable();

    public String toExecStatement();

    public T getObject();

}
