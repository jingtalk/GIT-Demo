package com.jing.sample.test.component.aac;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/13
 *     desc   : 使用 ViewModel 驱动 UI，该模块会被 UI 组件引用；
 *     version: 1.0
 * </pre>
 */
public class UserProfileViewModel extends ViewModel {

//    private String userId;
//    private User user;
    private LiveData<User> user;
    private UserRepository userRepo;

//    public void init(String userId) {
//        this.userId = userId;
//    }

//    public User getUser() {
//        return user;
//    }

    @Inject
    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public LiveData<User> getUser() {
        return this.user;
    }

    public void init(String userId) {
        if (this.user != null) {
            // ViewModel 是由 Fragment 创建的
            // 所以我们知道 userId 不会改变
            return;
        }
        user = userRepo.getUser(userId);
    }
}
