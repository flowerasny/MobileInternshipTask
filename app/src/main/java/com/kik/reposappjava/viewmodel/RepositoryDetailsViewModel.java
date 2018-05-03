package com.kik.reposappjava.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.kik.reposappjava.model.UserReposRepository;
import com.kik.reposappjava.model.data.Repository;

import java.util.List;

public class RepositoryDetailsViewModel extends ViewModel{

    private UserReposRepository repository = UserReposRepository.getInstance();

    public RepositoryDetailsViewModel() {
    }

    public LiveData<Repository> getRepo(String name) {
        return Transformations.map(
                repository.getUserRepositories(),
                repositories -> getSpecifiedRepository(repositories, name)
        );
    }

    private Repository getSpecifiedRepository(List<Repository> repositories, String name) {
        return repositories.stream()
                .filter(repository -> name.matches(repository.getName()))
                .findAny()
                .orElse(null);
    }
}
