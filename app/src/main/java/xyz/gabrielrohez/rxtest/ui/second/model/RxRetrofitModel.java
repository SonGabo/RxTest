package xyz.gabrielrohez.rxtest.ui.second.model;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
                        .map(new Function<List<GitHubRepo>, List<GitHubRepo>>() {
                            @Override
                            public List<GitHubRepo> apply(List<GitHubRepo> gitHubRepos) {
                                Collections.sort(gitHubRepos, new Comparator<GitHubRepo>() {
                                    @Override
                                    public int compare(GitHubRepo o1, GitHubRepo o2) {
                                        return o2.getName().compareTo(o1.getName());
                                    }
                                });
                                return gitHubRepos;
                            }
                        })
                        .subscribe(
                                listener::setData,
                                throwable -> Log.d(TAG, "error: "+throwable.getMessage())
                        )
        );
    }

    @Override
    public void getReposFilter(RxRetrofitPresenterIn.Listener listener, CompositeDisposable compositeDisposable) {

        compositeDisposable.add(
                WebService
                        .getInstance()
                        .createService()
                        .getReposRx("JakeWharton")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .flatMap(new Function<List<GitHubRepo>, ObservableSource<GitHubRepo>>() {
                            @Override
                            public ObservableSource<GitHubRepo> apply(List<GitHubRepo> gitHubRepos) throws Exception {
                                return Observable.fromIterable(gitHubRepos);
                            }
                        })
                        .filter(new Predicate<GitHubRepo>() {
                            @Override
                            public boolean test(GitHubRepo gitHubRepo) throws Exception {
                                if (gitHubRepo.getLanguage() != null && gitHubRepo.getLanguage().equals("Kotlin"))
                                    return true;
                                else
                                    return false;
                            }
                        })
                        .subscribe(new Consumer<GitHubRepo>() {
                            @Override
                            public void accept(GitHubRepo gitHubRepo) throws Exception {
                                listener.setDataFilter(gitHubRepo);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d(TAG, "error: "+throwable.getMessage());
                            }
                        })
        );
    }
}
