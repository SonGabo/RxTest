package xyz.gabrielrohez.rxtest.ui.second.model;

import io.reactivex.disposables.CompositeDisposable;
import xyz.gabrielrohez.rxtest.ui.second.presenter.RxRetrofitPresenterIn;

public interface RxRetrofitModelIn {
    void getRepos(RxRetrofitPresenterIn.Listener listener, CompositeDisposable compositeDisposable);
}
