package com.kik.reposappjava.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.kik.reposappjava.model.UserReposRepository;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<String> userName = new MutableLiveData<>();
    private UserReposRepository repository = UserReposRepository.getInstance();

    public MainActivityViewModel() {
    }

    public void setUserName(String userName) {
        this.userName.setValue(userName);
    }

    public LiveData<Boolean> getUserExists() {
        return Transformations.switchMap(userName, name -> {
            repository.loadReposForUser(name);
            return repository.getUserExists();
        });
    }

    public void cleanSearch(){
        repository.cleanUserExists();
    }
}
