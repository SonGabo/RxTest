package xyz.gabrielrohez.rxtest.ui.second.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import xyz.gabrielrohez.rxtest.databinding.ActivityRxRetrofitBinding;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;
import xyz.gabrielrohez.rxtest.ui.second.adapter.RepositoryAdapter;
import xyz.gabrielrohez.rxtest.ui.second.presenter.RxRetrofitPresenter;

public class RxRetrofitActivity extends AppCompatActivity implements RxRetrofitView {

    private RepositoryAdapter adapter;
    private List<GitHubRepo> gitHubList;
    private RxRetrofitPresenter presenter;
    private ActivityRxRetrofitBinding binding;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RxRetrofitPresenter(this);
        compositeDisposable = new CompositeDisposable();
        binding = ActivityRxRetrofitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpRecycler();
        presenter.getRepos(compositeDisposable);
    }

    private void setUpRecycler() {
        gitHubList = new ArrayList<>();
        adapter = new RepositoryAdapter(gitHubList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(List<GitHubRepo> gitHubRepos) {
        adapter.setData(gitHubRepos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();    //  memory leak
    }
}
