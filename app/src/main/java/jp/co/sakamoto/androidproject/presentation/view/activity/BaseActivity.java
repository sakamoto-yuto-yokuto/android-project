package jp.co.sakamoto.androidproject.presentation.view.activity;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.reactivex.disposables.CompositeDisposable;
import jp.co.sakamoto.androidproject.presentation.PauseHandler;

public class BaseActivity extends AppCompatActivity {
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
