package com.kik.reposappjava.model.di;

import com.kik.reposappjava.model.data.GitHubAPIClient;

import dagger.Component;

@Component(modules = GitHubAPIClientModule.class)
public interface GitHubAPIClientComponent {
    GitHubAPIClient getGitHubAPIClient();
}
