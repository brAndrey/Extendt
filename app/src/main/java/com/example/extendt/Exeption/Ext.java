package com.example.extendt.Exeption;

import android.util.Log;

public class Ext  extends start{

    int intedg;

    public void Ext(int intedg){
        this.intedg = intedg;
    }

    public int summ(int integer){
        Log.e(this.getClass().getSimpleName(),new Throwable().getStackTrace()[0].getMethodName()+" "+integer);
        integer++;
        return plus(integer);
    }
}
