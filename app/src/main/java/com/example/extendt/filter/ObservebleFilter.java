package com.example.extendt.filter;

import com.example.extendt.data.DataSource;
import com.example.extendt.data.Task;
import com.example.extendt.utils.ObserveblePrint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class ObservebleFilter extends ObserveblePrint {

    private  String TAG;
    Long startTime = System.currentTimeMillis();

    public ObservebleFilter(){

     //PrintObservebleTask(FromFilterString(),TAG);

     //PrintObservebleTask(FromFilterField(),TAG);

    //PrintObservebleTask(FromDistinct(),TAG);

        PrintObservebleTask(FromIterable(),TAG);

   //  new ObservebleFilter2();

    }


    private Observable FromFilterString() {
        TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";
        Logs(TAG,"*******************");
        Observable<Task> taskObservable = Observable
                .interval(1, TimeUnit.SECONDS)
                .fromIterable(DataSource.createTasksList())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        if (task.getDescription().equals("Walk the dog")){
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return taskObservable;

    }


    private  Observable FromFilterField(){
        TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";
        Logs(TAG,"*******************");
        Observable<Task> taskObservable = Observable.fromIterable(
                DataSource.createTasksList())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        return task.isComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return taskObservable;

    }

    private  Observable FromDistinct(){
      TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";

        Observable<Task> taskObserveble = Observable.fromIterable(DataSource.createTasksList())
                .distinct(new Function<Task, String>() {
                    @Override
                    public String apply(Task task) throws Exception {
                        return task.getDescription();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return taskObserveble;
    }

    private Observable FromTake() {
        TAG = String.valueOf(new Object(){}.getClass().getEnclosingMethod().getName()+"  ");
        Observable<Task> taskObservable = Observable.fromIterable(DataSource.createTasksList())
                .take(3) // берет первые 3 из Observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
                return taskObservable;
    }

    private Observable FromIterable() {
        TAG = new Object(){}.getClass().getEnclosingMethod().getName()+"  ";

        Observable<Task> taskObservable = Observable.fromIterable(DataSource.createTasksList())
                .takeWhile(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {
                        return task.isComplete(); // выбирает Observable пока не false
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return taskObservable;
    }
}
