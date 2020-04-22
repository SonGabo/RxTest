package xyz.gabrielrohez.rxtest.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;

public interface WebServiceApi {

    //  without Rx
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepos(@Path("user") String user);

    //  with Rx
    @GET("/users/{user}/repos")
    Single<List<GitHubRepo>> getReposRx(@Path("user") String user);
}
