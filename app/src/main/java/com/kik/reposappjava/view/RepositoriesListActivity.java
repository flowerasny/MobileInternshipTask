package com.kik.reposappjava.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kik.reposappjava.R;
import com.kik.reposappjava.model.data.Repository;
import com.kik.reposappjava.view.utils.RepositoriesListAdapter;
import com.kik.reposappjava.viewmodel.RepositoriesListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesListActivity extends AppCompatActivity {
    private static final String REPOSITORY_NAME = "REPOSITORY_NAME";

    @BindView(R.id.rv_repositories)
    RecyclerView repositoriesRecyclerView;
    @BindView(R.id.tv_no_public_repos)
    TextView tvNoPublicRepos;

    RepositoriesListAdapter adapter;
    RepositoriesListViewModel repositoriesListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);

        ButterKnife.bind(this);

        configureRecyclerView();
        observeViewModel();
    }

    private void configureRecyclerView() {
        repositoriesRecyclerView.setHasFixedSize(true);
        repositoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        repositoriesRecyclerView.setAdapter(createAdapter());
    }

    private RecyclerView.Adapter createAdapter() {
        adapter = new RepositoriesListAdapter(this);
        adapter.setOnItemClickListener(this::openDetailsActivity);
        return adapter;
    }

    private void openDetailsActivity(String repositoryName) {
        Intent intent = new Intent(this, RepositoryDetailsActivity.class);
        intent.putExtra(REPOSITORY_NAME, repositoryName);
        startActivity(intent);
    }

    private void observeViewModel() {
        repositoriesListViewModel = ViewModelProviders.of(this).get(RepositoriesListViewModel.class);
        repositoriesListViewModel.getRepositories().observe(this, this::setListToAdapter);
    }

    private void setListToAdapter(List<Repository> repositories) {
        if (repositories.isEmpty()){
            noPublicRepositoriesNotification();
        }else {
            adapter.updateRepositories(repositories);
        }
    }

    private void noPublicRepositoriesNotification() {
        tvNoPublicRepos.setVisibility(View.VISIBLE);
    }
}
