package xyz.gabrielrohez.rxtest.ui.second.model;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xyz.gabrielrohez.rxtest.api.WebService;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;
import xyz.gabrielrohez.rxtest.ui.second.presenter.RxRetrofitPresenterIn;

public class RxRetrofitModel implements RxRetrofitModelIn {

    private static final String TAG = "RxRetrofitModel";

    @Override
    public void getRepos(RxRetrofitPresenterIn.Listener listener, CompositeDisposable compositeDisposable) {

        compositeDisposable.add(
                WebService.getInstance()
                        .createService()
                        .getReposRx("JakeWharton")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                listener::setData,
                                throwable -> Log.d(TAG, "error: "+throwable.getMessage())
                        )
        );
    }
}
