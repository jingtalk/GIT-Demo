package com.jing.sample.test.component.aac;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/13
 *     desc   : 功能
 *     version: 1.0
 * </pre>
 */
public interface Webservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);

}
