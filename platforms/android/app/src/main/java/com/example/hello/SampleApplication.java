package com.example.hello;

import android.app.Application;

import io.mob.resu.reandroidsdk.AppConstants;
import io.mob.resu.reandroidsdk.ReAndroidSDK;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppConstants.LogFlag =true;
        ReAndroidSDK.getInstance(this);
    }
}
