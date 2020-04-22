package xyz.gabrielrohez.rxtest.ui.second.view;

import java.util.List;

import xyz.gabrielrohez.rxtest.model.GitHubRepo;

public interface RxRetrofitView {
    void setData(List<GitHubRepo> gitHubRepos);
}
