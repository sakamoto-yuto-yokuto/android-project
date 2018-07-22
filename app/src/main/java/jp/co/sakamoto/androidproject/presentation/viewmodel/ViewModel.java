package jp.co.sakamoto.androidproject.presentation.viewmodel;

import android.databinding.BaseObservable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class ViewModel extends BaseObservable implements Disposable {
    protected CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    public void dispose() {
        this.subscriptions.dispose();
    }

    @Override
    public boolean isDisposed() {
        return this.subscriptions.isDisposed();
    }
}

