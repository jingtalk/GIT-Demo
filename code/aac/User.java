package com.jing.sample.test.component.aac;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/13
 *     desc   : 功能
 *     version: 1.0
 * </pre>
 */

@Entity
public class User {
    @PrimaryKey
    private int id;
    private String name;
    private String lastName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
