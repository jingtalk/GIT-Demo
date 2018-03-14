package com.jing.sample.test.component.aac;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/13
 *     desc   : 功能
 *     version: 1.0
 * </pre>
 */
@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
