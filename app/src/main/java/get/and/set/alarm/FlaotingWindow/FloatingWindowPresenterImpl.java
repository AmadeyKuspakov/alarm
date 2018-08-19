package get.and.set.alarm.FlaotingWindow;

import android.app.WallpaperManager;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import get.and.set.alarm.R;
import get.and.set.alarm.ServicesManager;

/**
 * Created by Amadey on 8/14/2018.
 */

public class FloatingWindowPresenterImpl implements FloatingWindowPresenter{

    private FloatingWindowService floatingWindowService;
    private ServicesManager servicesManager;
    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if(i == EditorInfo.IME_ACTION_DONE && arePhrasesSimilar(textView.getText().toString())){
                floatingWindowService.stop();
            }
            return false;
        }
    };

    public FloatingWindowPresenterImpl(FloatingWindowService floatingWindowService, ServicesManager servicesManager){
        this.servicesManager = servicesManager;
        this.floatingWindowService = floatingWindowService;
    }

    @Override
    public WindowManager.LayoutParams getWindowParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        layoutParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        if(Build.VERSION.SDK_INT>25){
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.OPAQUE;
        return layoutParams;
    }

    @Override
    public View getLayoutAsView(int id) {
        View view = servicesManager.getLayoutInflater().inflate(id,null);
        EditText editText = view.findViewById(R.id.editText);
        editText.setOnEditorActionListener(onEditorActionListener);
        if(Build.VERSION.SDK_INT>15){
            view.setBackground(WallpaperManager.getInstance(servicesManager.getContext()).getFastDrawable());
        }else{
            view.setBackgroundDrawable(WallpaperManager.getInstance(servicesManager.getContext()).getFastDrawable());
        }
        return view;
    }

    private boolean arePhrasesSimilar(String typedInPhrase){
        return floatingWindowService.getPhrase().equalsIgnoreCase(typedInPhrase);
    }

    @Override
    public void startAlarm() {
        startVibration();
        startMusic();
    }

    private void startVibration(){
        Vibrator vibrator = servicesManager.getVibrator();
        long[] pattern = {0, 100, 1000};
        vibrator.vibrate(pattern, 0);
    }

    private void startMusic(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        MediaPlayer mediaPlayer = MediaPlayer.create(servicesManager.getContext(), uri);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

}
