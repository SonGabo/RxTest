package xyz.gabrielrohez.rxtest.ui.second.presenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;

public interface RxRetrofitPresenterIn {

    interface Listener{
        void setData(List<GitHubRepo> gitHubRepos);

        void setDataFilter(GitHubRepo gitHubRepo);
    }

    interface Presenter{

        void getRepos(CompositeDisposable compositeDisposable);
        void getReposFilter(CompositeDisposable compositeDisposable);
        void getAverageStars(CompositeDisposable compositeDisposable);
    }
}
