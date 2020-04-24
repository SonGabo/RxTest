package xyz.gabrielrohez.rxtest.ui.second.presenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;
import xyz.gabrielrohez.rxtest.ui.second.model.RxRetrofitModel;
import xyz.gabrielrohez.rxtest.ui.second.view.RxRetrofitView;

public class RxRetrofitPresenter implements RxRetrofitPresenterIn.Listener, RxRetrofitPresenterIn.Presenter {

    private RxRetrofitView view;
    private RxRetrofitModel model;

    public RxRetrofitPresenter(RxRetrofitView view) {
        this.view = view;
        model = new RxRetrofitModel();
    }

    @Override
    public void getRepos(CompositeDisposable compositeDisposable) {
        model.getRepos(this, compositeDisposable);
    }

    @Override
    public void getReposFilter(CompositeDisposable compositeDisposable) {
        model.getReposFilter(this, compositeDisposable);
    }

    @Override
    public void getAverageStars(CompositeDisposable compositeDisposable) {
        model.getAverageStars(this, compositeDisposable);
    }

    @Override
    public void setData(List<GitHubRepo> gitHubRepos) {
        if (view != null)
            view.setData(gitHubRepos);
    }

    @Override
    public void setDataFilter(GitHubRepo gitHubRepo) {
        if (view != null)
            view.setDataFilter(gitHubRepo);
    }
}
