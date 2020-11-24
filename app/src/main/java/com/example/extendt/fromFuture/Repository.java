package com.example.extendt.fromFuture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Repository {

    private static Repository instance;

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }


    public LiveData<ResponseBody> makeReactiveQuery(){
        // при помощи fromPublisher превращаем Observeble в LiveData
        return LiveDataReactiveStreams.fromPublisher(
                ServiceGenerator.getRequestApi()
                .makeQuery() // from RequestApi.java
                .subscribeOn(Schedulers.io())); // убираем в потов ввода вывода
    }

}
