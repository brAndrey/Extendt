package com.example.extendt.filter;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class ObservebleFilter2 {
    private  String TAG;

    public ObservebleFilter2() {

        PrintObserveble(FromFilterRange(), TAG);

    }

    private Observable FromFilterRange() {
        TAG = new Object() {}.getClass().getEnclosingMethod().getName() + "  ";

        Observable<Integer> rangInt = Observable.range(0, 20)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return (integer % 2) == 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return rangInt;

    }


    private void PrintObserveble(Observable fromObserver, String tag) {

        fromObserver.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logs(tag, "onNext:  " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void Logs(String TAG, String text) {
        Log.e(TAG, text );//+ " " + System.currentTimeMillis());
    }
}
