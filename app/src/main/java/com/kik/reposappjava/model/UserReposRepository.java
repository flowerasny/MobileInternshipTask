package com.kik.reposappjava.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.kik.reposappjava.model.di.DaggerGitHubAPIClientComponent;
import com.kik.reposappjava.model.di.GitHubAPIClientComponent;
import com.kik.reposappjava.model.data.GitHubAPIClient;
import com.kik.reposappjava.model.data.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserReposRepository {

    private static UserReposRepository userReposRepository = null;

    GitHubAPIClient gitHubAPIClient;

    public MutableLiveData<Boolean> userExists = new MutableLiveData<>();
    public MutableLiveData<List<Repository>> userRepositories= new MutableLiveData<>();

    public UserReposRepository() {
        GitHubAPIClientComponent gitHubAPIClientComponent = DaggerGitHubAPIClientComponent.builder().build();
        gitHubAPIClient = gitHubAPIClientComponent.getGitHubAPIClient();
    }

    public UserReposRepository(GitHubAPIClient gitHubAPIClient) {
        this.gitHubAPIClient = gitHubAPIClient;
    }

    public static UserReposRepository getInstance(){
        if (userReposRepository == null){
            userReposRepository = new UserReposRepository();
        }
        return userReposRepository;
    }
    public void loadReposForUser(final String user) {
        Call<List<Repository>> call = gitHubAPIClient.loadRepos(user);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()){
                    userExists.setValue(true);
                    userRepositories.setValue(response.body());
                }
                else userExists.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
    }

    public LiveData<List<Repository>> getUserRepositories() {
        return userRepositories;
    }

    public LiveData<Boolean> getUserExists() {
        return userExists;
    }

    public void cleanUserExists() {
        userExists.setValue(null);
    }
}
