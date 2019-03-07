package com.jing.sample.test.feature.crash;

import android.app.Application;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/05
 *     desc   : 功能
 *     version: 1.0
 * </pre>
 */
public class CrashApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
