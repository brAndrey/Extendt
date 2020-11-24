package com.example.extendt.observerablesUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.extendt.MainActivity;
import com.example.extendt.R;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class ObserverUI extends AppCompatActivity {

    private static final String TAG = ObserverUI.class.getSimpleName();

    CompositeDisposable disposable = new CompositeDisposable();
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_ui);

        Button button = findViewById(R.id.buttonThrottleFirst);
        TextView textView = findViewById(R.id.textView3);

        SearchView searchView = findViewById(R.id.searchView);

        // пример по buffer
        //******************************
        RxView.clicks(findViewById(R.id.buttonBuffer))
                .map(new Function<Unit, Integer>() {
                    @Override
                    public Integer apply(Unit unit) throws Exception {
                        return 1;
                    }
                })
                .buffer(4, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d); // add to disposables to you can clear in onDestroy
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        textView.setText(String.valueOf(integers.size()));
                        Log.d("RxView.clicks ", "onNext: You clicked " + integers.size() + " times in 4 seconds!");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //******************************

        Observable<String> observableQueryText = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if (!emitter.isDisposed()) {
                                    emitter.onNext(newText);
                                }
                                return false;
                            }
                        });
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());

        observableQueryText.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(String s) {
                Log.d("observableQueryText ", "onNext: time  since last request: " + (System.currentTimeMillis() - timeSinceLastRequest));
                Log.d("observableQueryText ", "onNext: search query: " + s);
                timeSinceLastRequest = System.currentTimeMillis();

                // method for sending a request to the server
                sendRequestToServer(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        //******************************
        RxView.clicks(button)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Unit unit) {
                        Log.d("throttleFirst", "onNext: time since last clicked: " + (System.currentTimeMillis() - timeSinceLastRequest));
                        someMethod(); // Execute some method when a click is registered
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //******************************

    }

    private void someMethod() {
        timeSinceLastRequest = System.currentTimeMillis();
        Toast.makeText(this, "You clicked the button", Toast.LENGTH_SHORT).show();

    }

    private void sendRequestToServer(String s) {
        // do nothing
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

}
