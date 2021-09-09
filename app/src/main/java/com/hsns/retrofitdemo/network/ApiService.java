package com.hsns.retrofitdemo.network;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    /**
     * 请求得到用户信息
     * @param username 用户名
     * @param password 密码
     * @return  请求结果
     */
  @GET("/test")
    Observable<String> login(@Query("username") String username, @Query("password") String password);
}
