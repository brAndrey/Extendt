package com.example.extendt;





import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class ObservableIntervalTimer {
    public ObservableIntervalTimer(){

        Internal();

        Timer();

    }

    private void Timer() {
        String TAG= new Object(){}.getClass().getEnclosingMethod().getName()+"  ";


        Observable<Long> TimeObserver = Observable
                .timer(3,TimeUnit.SECONDS) // который излучает один конкретный элемент через указанный вами промежуток времени.
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        TimeObserver.subscribe(new Observer<Long>() {
            long time=0;

            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG,"onSubscribe ");
                time = System.currentTimeMillis() / 1000;
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext: " + ((System.currentTimeMillis() / 1000) - time) + " seconds have elapsed." );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void Internal() {

        String TAG= new Object(){}.getClass().getEnclosingMethod().getName()+"  ";

        Observable<Long> intervalObserver = Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeWhile(new Predicate<Long>() { // останавливаем процесс, если прошло более 5 секунд
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong<=5;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());


        intervalObserver.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG," onNext: interval: "+aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
