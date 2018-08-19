package get.and.set.alarm.AlarmRepository;

import java.util.ArrayList;

import get.and.set.alarm.AlarmRepository.Specifications.Specification;
import get.and.set.alarm.ProjectEntities.Alarm;

/**
 * Created by Amadey on 6/29/2018.
 */

public interface AlarmRepository {

    public boolean insert(Specification specification);

    public boolean update(Specification specification);

    public boolean delete(Specification specification);

    public ArrayList<Alarm> get(Specification specification);

}
