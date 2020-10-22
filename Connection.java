package com.example.projectsdetails;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    public static final String BASE_URL="http://192.168.137.1/retrofit1/";
//    public static final String BASE_URL="http://www.abhis.pyramidtechnoindustries.com/retrofit1/";

    public static Retrofit retrofit = null;

    public static Retrofit getApiCon(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        System.out.println("Retrofit returns: "+retrofit);
        return retrofit;
    }
}