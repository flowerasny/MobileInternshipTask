package com.kik.reposappjava.model.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPIClient {

    @GET("users/{user}/repos")
    Call<List<Repository>> loadRepos(@Path("user") String userName);

}
