package com.example.extendt.Exeption;

import android.util.Log;

public class start {

    public int plus(int i){
        Log.e(this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()+" "+i);
        return i++;
    }
}
