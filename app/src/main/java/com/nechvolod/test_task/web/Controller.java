package com.nechvolod.test_task.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://rickandmortyapi.com";

    public static RickAndMortyApi getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RickAndMortyApi rickAndMortyApi = retrofit.create(RickAndMortyApi.class);

        return rickAndMortyApi;
    }
}
