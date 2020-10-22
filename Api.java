package com.example.projectsdetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("addProject.php")
    Call<Project> saveProject
            (@Query("p_name") String name,
             @Query("p_dur") String dura,
             @Query("p_date") String date);

    @GET("getAllProject.php")
    Call<List<Project>> getProjects();
}