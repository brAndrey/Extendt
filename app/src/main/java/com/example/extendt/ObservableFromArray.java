package com.example.extendt;

import android.util.Log;

import com.example.extendt.data.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ObservableFromArray {



    public ObservableFromArray(){

        Task[] list = new Task[5];
        list[0] = (new Task("Take out the trash", true, 3));
        list[1] = (new Task("Walk the dog", false, 2));
        list[2] = (new Task("Make my bed", true, 1));
        list[3] = (new Task("Unload the dishwasher", false, 0));
        list[4] = (new Task("Make dinner", true, 5));

        FromArray(list);

        FromItterable(list);


    }

    private void FromItterable(Task[] list) {

        List<Task> list1 = new ArrayList<>();
        
        list1= Arrays.asList(list);

        String TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";

        Observable<Task> taskObserver = Observable
                .fromIterable(list1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObserver.subscribe(new Observer<Task>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: : " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void FromArray(Task[] list) {
        String TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";

        Observable<Task> taskObserver = Observable
            .fromArray(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

        taskObserver.subscribe(new Observer<Task>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: : " + task.getDescription());
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
