package com.kik.reposappjava.view.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kik.reposappjava.R;
import com.kik.reposappjava.model.data.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.RepositoryViewHolder> {

    private Context context;
    private List<Repository> repositories = new ArrayList<>();
    private OnRepositoryItemClickListener onRepositoryItemClickListener;

    public RepositoriesListAdapter(Context context) {
        this.context = context;
    }

    public void updateRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRepositoryItemClickListener onItemClickListener){
        this.onRepositoryItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.repo_rv_item, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);

        holder.name.setText(repository.getName());
        holder.watchers.setText(String.valueOf(repository.getWatchers()));
        holder.stars.setText(String.valueOf(repository.getStars()));
        holder.forks.setText(String.valueOf(repository.getForks()));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public class RepositoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_repo_name)
        TextView name;
        @BindView(R.id.tv_watchers)
        TextView watchers;
        @BindView(R.id.tv_stars)
        TextView stars;
        @BindView(R.id.tv_forks)
        TextView forks;

        RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRepositoryItemClickListener.onItemClicked(name.getText().toString());
        }
    }

}
