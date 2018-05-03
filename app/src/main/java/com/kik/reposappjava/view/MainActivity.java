package com.kik.reposappjava.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.kik.reposappjava.R;
import com.kik.reposappjava.viewmodel.MainActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.pb_loading_repos)
    ProgressBar pbLoadingRepos;

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        subscribeViewModel();
    }

    private void subscribeViewModel() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getUserExists().observe(
                this,
                userExists -> {
                    if (userExists != null) {
                        loadProgressbarGone();
                        if (userExists) {
                            startListRepositoryActivity();
                        } else {
                            userUnknownNotification();
                        }
                    }
                });
    }

    private void loadProgressbarGone() {
        pbLoadingRepos.setVisibility(View.GONE);
    }

    private void startListRepositoryActivity() {
        startActivity(new Intent(this, RepositoriesListActivity.class));
    }

    private void userUnknownNotification() {
        etUserName.setError(getString(R.string.user_error));
    }

    @OnClick(R.id.btn_load_repos)
    public void loadRepos() {
        loadProgressbarVisible();
        mainActivityViewModel.setUserName(etUserName.getText().toString());
    }

    private void loadProgressbarVisible() {
        pbLoadingRepos.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityViewModel.cleanSearch();
    }
}
