package jp.co.sakamoto.androidproject.presentation.view.helper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

/**
 *  RecyclerViewが最下部に到達したことを通知するHelper.
 *  スクロール最下部に到達時のRecyclerViewの項目の追加読み込み等に使用する.
 */
public class RxRecyclerViewScrollHelper {
    private Subject<RecyclerViewScrollEvent, RecyclerViewScrollEvent> subject = PublishSubject.create();
    private Subscription subscription = Subscriptions.empty();

    public Observable<RecyclerViewScrollEvent> observable() {
        return subject;
    }

    public void start(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        subscription.unsubscribe();
        subscription = RxRecyclerView.scrollEvents(recyclerView).subscribe(event -> {
            int totalItemCount = linearLayoutManager.getItemCount();
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if (totalItemCount - 1 <= lastVisibleItemPosition) {
                subject.onNext(event);
            }
        });
    }

    public void stop() {
        subscription.unsubscribe();
    }
}