package get.and.set.alarm.FlaotingWindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

import get.and.set.alarm.R;
import get.and.set.alarm.ServicesManager;

public class FloatingWindowServiceImpl extends Service implements FloatingWindowService{

    private WindowManager windowManager;
    private View view;
    private String phrase;

    public FloatingWindowServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        phrase = intent.getStringExtra("phrase");
        ServicesManager servicesManager = new ServicesManager(this.getBaseContext());
        windowManager = servicesManager.getWindowManager();
        FloatingWindowPresenter floatingWindowPresenter = new FloatingWindowPresenterImpl(this, servicesManager);
        view = floatingWindowPresenter.getLayoutAsView(R.layout.floating_window);
        windowManager.addView(view, floatingWindowPresenter.getWindowParams());
        floatingWindowPresenter.startAlarm();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(view);
    }

    @Override
    public void stop() {
        stopSelf();
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

}
