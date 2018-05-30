package com.jordicros997.dsaexam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jordi on 30/05/2018.
 */

public interface GithubAPI {
    @GET("books")
    Call<List<Llibre>> getLlibres();
    @GET("books/{id}/")
    Call<Llibre> getBookDetails(@Path("id") String id);

}
