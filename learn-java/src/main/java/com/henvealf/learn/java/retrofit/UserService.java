package com.henvealf.learn.java.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * 访问 rest 接口的 服务。
 * @author hongliang.yin/Henvealf
 * @date 2020/5/11
 */
public interface UserService {

    @GET("/users")
    public Call<List<User>> getUsers(@Query("per_page") int per_page,
                                     @Query("page") int page);

    /**
     * path 用于填充路径参数。
     * @param username
     * @return
     */
    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);

}
