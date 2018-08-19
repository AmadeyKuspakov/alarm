package get.and.set.alarm.FlaotingWindow;

import android.view.View;
import android.view.WindowManager;

/**
 * Created by Amadey on 8/14/2018.
 */

public interface FloatingWindowPresenter {

    public WindowManager.LayoutParams getWindowParams();

    public View getLayoutAsView(int id);

    public void startAlarm();

}
