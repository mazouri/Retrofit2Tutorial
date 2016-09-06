package com.mazouri.retrofit2tutorial.rest;

import com.mazouri.retrofit2tutorial.models.GitResult;
import com.mazouri.retrofit2tutorial.models.Item;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangdongdong on 16/9/6.
 */
public interface GitApiInterface {

    @Headers("User-Agent: Retrofit2 Tutorial")
    @GET("search/users")
    Call<GitResult> getUsersNamedTom(@Query("q") String name);

    @POST("user/create")
    Call<Item> createUser(@Body String name, @Body String email);

    @PUT("user/{id}/update")
    Call<Item> updateUser(@Path("id") String id, @Body Item user);
}
