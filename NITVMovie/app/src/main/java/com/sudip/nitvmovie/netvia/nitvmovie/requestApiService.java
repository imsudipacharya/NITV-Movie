package com.sudip.nitvmovie.netvia.nitvmovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class requestApiService {

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(allApiLinks.BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static nitvMovieApi movieApi = retrofit.create(nitvMovieApi.class);

    public nitvMovieApi getMovieApi(){
        return movieApi;
    }
}
