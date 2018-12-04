package jp.co.sakamoto.androidproject.presentation.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import io.reactivex.disposables.CompositeDisposable;
import jp.co.sakamoto.androidproject.presentation.view.helper.PauseHandler;

abstract public class BaseActivity extends AppCompatActivity {
    protected CompositeDisposable subscriptions = new CompositeDisposable();

    @SuppressLint("HandlerLeak")
    private PauseHandler pauseHandler = new PauseHandler() {
        @Override
        protected boolean storeMessage(Message message) {
            return true;
        }

        @Override
        protected void processMessage(Message message) {
            processPauseMessage(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO:ステータスバーを透過する場合はコメントを解除
//        translucentStatusBar();
    }

    private void translucentStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.subscriptions.dispose();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void sendPauseMessage(int what) {
        this.sendPauseMessage(what, null);
    }

    protected void sendPauseMessage(int what, Bundle bundle) {
        Message message = pauseHandler.obtainMessage(what);
        if (bundle != null) {
            message.setData(bundle);
        }
        pauseHandler.sendMessage(message);
    }

    protected void processPauseMessage(Message message) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseHandler.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pauseHandler.resume();
    }
}
