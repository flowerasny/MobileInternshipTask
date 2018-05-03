package com.kik.reposappjava.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kik.reposappjava.R;
import com.kik.reposappjava.model.data.Repository;
import com.kik.reposappjava.viewmodel.RepositoryDetailsViewModel;

import java.util.Optional;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryDetailsActivity extends AppCompatActivity {
    private static final String REPOSITORY_NAME = "REPOSITORY_NAME";

    @BindView(R.id.tv_repo_name)
    TextView tvName;
    @BindView(R.id.tv_repo_description)
    TextView tvDescription;
    @BindView(R.id.tv_repo_language)
    TextView tvLanguage;

    RepositoryDetailsViewModel repositoryDetailsViewModel;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        ButterKnife.bind(this);
        subscribeViewModel(getIntent().getStringExtra(REPOSITORY_NAME));
    }

    private void subscribeViewModel(String repo_name) {
        repositoryDetailsViewModel = ViewModelProviders.of(this).get(RepositoryDetailsViewModel.class);
        repositoryDetailsViewModel.getRepo(repo_name).observe(this, this::displayRepo);
    }

    private void displayRepo(Repository repository) {
        tvName.setText(repository.getName());
        tvDescription.setText(Optional.ofNullable(repository.getDescription()).orElse(getString(R.string.no_description)));
        tvLanguage.setText(Optional.ofNullable(repository.getLanguage()).orElse(getString(R.string.no_language)));
    }
}
