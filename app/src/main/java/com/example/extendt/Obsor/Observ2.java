package com.example.extendt.Obsor;

import com.example.extendt.data.DataSource;
import com.example.extendt.data.Task;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Observ2 {

    public Observ2() {
        obCreate();

    }

    private void obCreate() {

        final Task task = new Task("Walk the dog", false, 4);

        Observable<Task> singleTaskObservable = Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(ObservableEmitter<Task> emitter) throws Exception {
//                        if (!emitter.isDisposed()) {
//                            emitter.onNext(task);
//                            emitter.onComplete();
//                        }
//                    }
                        for (Task task1 : DataSource.createTasksList()) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(task1);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }


                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        singleTaskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(this.getClass().getSimpleName(),
                        " " + new Throwable().getStackTrace()[0].getMethodName() + "  "
                                + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Log.d(this.getClass().getSimpleName(), "onNext: single task: " + task.getDescription());
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
