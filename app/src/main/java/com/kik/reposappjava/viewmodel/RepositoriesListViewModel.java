package com.kik.reposappjava.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.kik.reposappjava.model.UserReposRepository;
import com.kik.reposappjava.model.data.Repository;

import java.util.List;

public class RepositoriesListViewModel extends ViewModel {

    private UserReposRepository repository = UserReposRepository.getInstance();

    public RepositoriesListViewModel() {
    }

    public LiveData<List<Repository>> getRepositories() {
        return Transformations.map(
                repository.getUserRepositories(),
                repoList -> repoList
        );
    }

}
