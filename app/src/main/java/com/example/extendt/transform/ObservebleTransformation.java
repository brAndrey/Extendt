package com.example.extendt.transform;

import android.util.Log;

import com.example.extendt.data.DataSource;
import com.example.extendt.data.Task;
import com.example.extendt.utils.ObserveblePrint;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ObservebleTransformation extends ObserveblePrint {
    private  String TAG;

    public ObservebleTransformation(){

       // PrintObservebleStr(ObsMap()," ObsMap ");

      //  PrintObservebleTask(MapTask(),TAG);

        ObserverleBuffer();
    }

    private void ObserverleBuffer() {

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io());

        taskObservable.buffer(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Task>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        Log.d(TAG, "onNext: bundle results: -------------------");
                        for(Task task: tasks) {
                            Log.d(TAG, "onNext: " + task.getDescription());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private Observable ObsMap() {
        TAG = new Object() {}.getClass().getEnclosingMethod().getName() + "  ";
        Logs(TAG, "*******************");

        // в этой функции подменяем элементы Task на значение из элемента
        Function<Task, String> extractDescriptionFunction = new Function<Task, String>() {
            @Override
            public String apply(Task task) throws Exception {
                Log.d(TAG, "apply: doing work on thread: " + Thread.currentThread().getName());
                return task.getDescription();
            }
        };

        Observable<String> extractDescriptionObservable = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .map(task -> task.getDescription())
                .observeOn(AndroidSchedulers.mainThread());

        // если сложные вычисления то лучше через вынесенную функцию
        // .map(extractDescriptionFunction)

        return extractDescriptionObservable;

    }

    private Observable MapTask() {
        TAG = new Object() {}.getClass().getEnclosingMethod().getName() + "  ";
        Logs(TAG, "*******************");

        Function<Task,Task> completeTaskFunction = new Function<Task, Task>() {
            @Override
            public Task apply(Task task) throws Exception {
                Log.d(TAG, "apply: doing work on thread: " + Thread.currentThread().getName());
                task.setComplete(true);
                return task;
            }
        };

        Observable<Task> completeTaskObserveble = Observable
                .fromIterable(DataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .map(completeTaskFunction)
                .observeOn(AndroidSchedulers.mainThread());

        return completeTaskObserveble;
    }
}
