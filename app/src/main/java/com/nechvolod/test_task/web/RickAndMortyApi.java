package com.nechvolod.test_task.web;

import com.nechvolod.test_task.model.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickAndMortyApi {
    @GET("/api/character/")
    Call<ResponseObject> getData(@Query("page") String pageNumber);
}
