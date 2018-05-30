package com.jordicros997.dsaexam;

import android.widget.ProgressBar;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jordi on 30/05/2018.
 */

public class Singleton {
    private static Singleton ourInstance;
    private Llibre selectedBook;
    private GithubAPI service ;
    public static Singleton getInstance() {
        if(ourInstance==null)
        {
            ourInstance = new Singleton();
            return ourInstance;
        }

        else return ourInstance;

    }
    private Singleton()
    {
        Retrofit retr = new Retrofit.Builder().baseUrl("http://api.dsamola.tk/")
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create()).build();
        this.service = retr.create(GithubAPI.class);
    }

    public GithubAPI getService() {
        return service;
    }

    public Llibre getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Llibre selectedBook) {
        this.selectedBook = selectedBook;
    }
}
