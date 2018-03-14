package com.jing.sample.test.component.aac;

import android.arch.lifecycle.LiveData;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/13
 *     desc   : 处理数据
 *
 *      Repository 模块看起来是不必要的，但是它起着一个重要的作用；它抽象了应用程序其它部分的数据源。
 *      现在 ViewModel 不知道数据是由 Webservice 获取的，这意味着可以根据需求将其切换为其它实现。
 *     version: 1.0
 * </pre>
 */
public class UserRepository {

    private Webservice webservice;

    // 缓存数据 - 进程没有被杀死的情况下
    private UserCache userCache;

    // ROOM 持久化
    private UserDao userDao;
    private Executor executor;

    // ...

    @Inject
    public UserRepository(Webservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    /*
    public LiveData<User> getUser(String userId) {

        LiveData<User> cached = userCache.getUser(userId);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<User> data = new MutableLiveData<>();
        userCache.put(userId, data);

        webservice.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // 为了简单起见省略错误的情况
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }
    */

    public LiveData<User> getUser(String userId) {

        refreshUser(userId);
        return userDao.load(userId);
    }

    private void refreshUser(final String userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                boolean userExists = userDao.hasUser(userId);
                boolean userExists = false;
                if (!userExists) {
                    try {
                        Response response = webservice.getUser(userId).execute();
                        userDao.save((User) response.body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
