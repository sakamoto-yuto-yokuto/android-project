package jp.co.sakamoto.androidproject.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseFragment extends Fragment implements Disposable {
    protected CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void dispose() {
        if (!this.subscriptions.isDisposed())
            this.subscriptions.dispose();
    }

    @Override
    public boolean isDisposed() {
        return this.subscriptions.isDisposed();
    }
}
