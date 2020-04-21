package xyz.gabrielrohez.rxtest.ui.second;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import xyz.gabrielrohez.rxtest.databinding.ListItemRepoBinding;
import xyz.gabrielrohez.rxtest.model.GitHubRepo;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<GitHubRepo> list;

    public RepositoryAdapter(List<GitHubRepo> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemRepoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvStars.setText(""+list.get(position).getStargazers_count());
        holder.binding.tvLanguage.setText(list.get(position).getLanguage());
        holder.binding.tvRepository.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<GitHubRepo> list){
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemRepoBinding binding;

        ViewHolder(ListItemRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
