package com.example.paarthbir.smartmeter;

import android.app.Application;

public class GlobalClass extends Application {
    private String mGlobalVarValue;

    public String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public void setGlobalVarValue(String str) {
        mGlobalVarValue = str;
    }
}
