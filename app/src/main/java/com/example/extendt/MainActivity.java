package com.example.extendt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.extendt.Exeption.Ext;
import com.example.extendt.Obsor.Observ2;
import com.example.extendt.Obsor.Observebler1;
import com.example.extendt.filter.ObservebleFilter;
import com.example.extendt.fromFuture.MainActivity2;
import com.example.extendt.observerablesUI.ObserverUI;
import com.example.extendt.transform.ObservebleTransformation;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //**********************************


        //*****************************

    }

    public void onClick(View view) {
        Ext ext = new Ext();
        Log.e(this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName() + " " + ext.summ(1));
    }

    public void onClickObsorver(View view) {
        new Observebler1();
    }

    public void onClickObsorver2(View view) {
        new Observ2();
    }



    private int longAction(String text) {
        Log.e("longAction", "");
        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(text);
    }



    public void onClickObsorverRange(View view) {
        new ObserverRange();
    }


    public void onClickObsorverIntervalTimer(View view) {
        new ObservableIntervalTimer();
    }

    public void onClickObsorverFromArray(View view) {
        new ObservableFromArray();

    }

    public void onClickFromPublisher(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void onClickObsorverFilter(View view) {
        new ObservebleFilter();
    }

    public void onClickObsorverTransformation(View view) {
        new ObservebleTransformation();
    }


    public void onClickObsorverUI(View view) {
        Intent intent = new Intent(MainActivity.this, ObserverUI.class);
        startActivity(intent);
    }
}