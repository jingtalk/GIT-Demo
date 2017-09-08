package com.jing.sample.notimportant;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 功能描述:
 *
 * @author : hakimhuang
 * @date : 2017/09/05
 * @version: 1.0
 */
public class MyApp extends Application {

    private static Context mAppCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppCtx = getApplicationContext();
    }

    @NonNull
    public static Context getContext() {
        return mAppCtx;
    }
}
