package com.example.extendt.utils;

import android.util.Log;

import com.example.extendt.data.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserveblePrint {

    private  String TAG;
    Long startTime = System.currentTimeMillis();
    int k=0;


    public void PrintObservebleTask(Observable taskObserv, String TAG){
        Logs(TAG,"++++++++++++++++++++++");

        taskObserv.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                k++;
                Logs(TAG, k+" onNext: This task matches the description: " + task.getDescription()+" "+task.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        } );

    }

    public void  PrintObservebleStr(Observable taskObserv, String TAG){
        Logs(TAG,"печатаем Observable");
        taskObserv.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Logs(TAG, k+" onNext: extracted description: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        } );

    }

    public void Logs (String TAG, String text){
        Log.e(TAG,text + " "+ (System.currentTimeMillis() - startTime)+" milisec");
    }
}
