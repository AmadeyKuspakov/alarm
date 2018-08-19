package get.and.set.alarm.AlarmRepository.Mappers;

import get.and.set.alarm.AlarmRepository.SQLite.SQLiteKeys;

/**
 * Created by Amadey on 6/29/2018.
 */

public interface Mapper<F, T> extends SQLiteKeys {

    public T map(F from);

}
