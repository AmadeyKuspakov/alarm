package get.and.set.alarm.AlarmRepository.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import get.and.set.alarm.AlarmRepository.AlarmRepository;
import get.and.set.alarm.AlarmRepository.Mappers.SQLiteMappers.CursorToAlarmMapper;
import get.and.set.alarm.AlarmRepository.Mappers.SQLiteMappers.AlarmToContentValuesMapper;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteCreationSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteDeleteByIdSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteDropTableSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteGetAllSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteInsertionSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.SQLiteSpecifications.SQLiteSpecificationsImplementation.SQLiteUpdateByIdSpecification;
import get.and.set.alarm.AlarmRepository.Specifications.Specification;
import get.and.set.alarm.ProjectEntities.Alarm;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 6/29/2018.
 */

public class SQLiteAlarmRepository extends SQLiteOpenHelper implements AlarmRepository {

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "alarmRepository";

    public SQLiteAlarmRepository(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(new SQLiteCreationSpecification().toExecStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(new SQLiteDropTableSpecification().toExecStatement());
        onCreate(sqLiteDatabase);
    }

    @Override
    public boolean insert(Specification specification) {
        SQLiteInsertionSpecification insertion = (SQLiteInsertionSpecification) specification;
        return this.getWritableDatabase().insert(insertion.getTable(), insertion.toExecStatement(), new AlarmToContentValuesMapper().map(insertion.getObject())) != -1;
    }

    @Override
    public boolean update(Specification specification) {
        SQLiteUpdateByIdSpecification update = (SQLiteUpdateByIdSpecification) specification;
        return this.getWritableDatabase().update(update.getTable(), new AlarmToContentValuesMapper().map(update.getObject()), update.toExecStatement(), null) > 0;
    }

    @Override
    public boolean delete(Specification specification) {
        SQLiteDeleteByIdSpecification delete = (SQLiteDeleteByIdSpecification) specification;
        return this.getWritableDatabase().delete(delete.getTable(), delete.toExecStatement(), null) > 0;
    }

    @Override
    public ArrayList get(Specification specification) {
        SQLiteGetAllSpecification getAll = (SQLiteGetAllSpecification) specification;
        Cursor cursor = this.getReadableDatabase().rawQuery(getAll.toExecStatement(), null);
        cursor.moveToFirst();
        ArrayList<Alarm> output = new ArrayList<>();
        int i = 0;
        while(i < cursor.getCount()){
            output.add(new CursorToAlarmMapper().map(cursor));
            cursor.moveToNext();
            i++;
        }
        cursor.close();
        return output;
    }
}
