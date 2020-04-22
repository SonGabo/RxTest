package xyz.gabrielrohez.rxtest.ui.second.presenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;

public interface RxRetrofitPresenterIn {

    interface Listener{
        void setData(List<GitHubRepo> gitHubRepos);
    }

    interface Presenter{

        void getRepos(CompositeDisposable compositeDisposable);
    }
}
