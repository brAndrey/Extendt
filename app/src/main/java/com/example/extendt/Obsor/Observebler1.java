package com.example.extendt.Obsor;

import android.util.Log;

import com.example.extendt.data.DataSource;
import com.example.extendt.data.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Observebler1 {

    // https://codingwithmitch.com/courses/rxjava-rxandroid-for-beginners/introduction-observables-and-observers/

    private static final String TAG=Observebler1.class.getSimpleName();

    // http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/CompositeDisposable.html
    CompositeDisposable disposables = new CompositeDisposable();
    // Одноразовый контейнер, который может удерживать несколько других одноразовых предметов
    // и предлагает сложность добавления и удаления O
    public Observebler1(){


        //создаём Observebler

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io()) // отправляет выполнение в пул потоков ввода-вывода. Тем самым не вешая UI поток
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {

                        Log.e(TAG, " test: " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                            Log.e(TAG, " test: " + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        // подписываемся и получаем результат
        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                Log.e(TAG, "onSubscribe ");
                disposables.add(disposable);
            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, " onNext: " + Thread.currentThread().getName());
                Log.d(TAG, "onNext: : " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " onError ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, " onComplete ");
            }
        });

        onDestroy();
    }

    public void onDestroy(){
        disposables.clear();
    };
}
