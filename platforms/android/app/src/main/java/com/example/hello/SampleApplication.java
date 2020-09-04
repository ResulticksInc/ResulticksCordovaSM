package com.example.hello;

import android.app.Application;

import io.mob.resu.reandroidsdk.AppConstants;
import io.mob.resu.reandroidsdk.ReAndroidSDK;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ReAndroidSDK.getInstance(this);
        AppConstants.LogFlag =true;
    }
}
