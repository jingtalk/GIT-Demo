package com.jing.sample.notimportant;


import android.app.Activity;
import android.support.annotation.BoolRes;
import android.support.annotation.StringRes;

public class ItemSample {

    protected String mTitle;
    protected String mDesc;
    protected boolean mIsNew = false;
    protected Class<? extends Activity> mActivityClass;

    public ItemSample(String title, String desc) {
        this.mTitle = title;
        this.mDesc = desc;
    }

    public ItemSample(String title, String desc, boolean flag, Class<? extends Activity> activityClass) {
        this.mTitle = title;
        this.mDesc = desc;
        this.mIsNew = flag;
        this.mActivityClass = activityClass;
    }

    public ItemSample(String title, String desc, Class<? extends Activity> activityClass) {
        this.mTitle = title;
        this.mDesc = desc;
        this.mActivityClass = activityClass;
    }

    public ItemSample(@StringRes int titleResId, @StringRes int descResId, boolean flag, Class<? extends Activity> activityClass) {
        this.mTitle = MyApp.getContext().getResources().getString(titleResId);
        this.mDesc = MyApp.getContext().getResources().getString(descResId);
        this.mIsNew = flag;
        this.mActivityClass = activityClass;
    }

    public ItemSample(@StringRes int titleResId, @StringRes int descResId, @BoolRes int newResId, Class<? extends Activity> activityClass) {
        this.mTitle = MyApp.getContext().getResources().getString(titleResId);
        this.mDesc = MyApp.getContext().getResources().getString(descResId);
        this.mIsNew = MyApp.getContext().getResources().getBoolean(newResId);;
        this.mActivityClass = activityClass;
    }

    public ItemSample(@StringRes int titleResId, @StringRes int descResId, Class<? extends Activity> activityClass) {
        this.mTitle = MyApp.getContext().getResources().getString(titleResId);
        this.mDesc = MyApp.getContext().getResources().getString(descResId);
        this.mActivityClass = activityClass;
    }
}
