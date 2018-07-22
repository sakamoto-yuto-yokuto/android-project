package jp.co.sakamoto.androidproject.domain.usecase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class Usecase implements Disposable {
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
