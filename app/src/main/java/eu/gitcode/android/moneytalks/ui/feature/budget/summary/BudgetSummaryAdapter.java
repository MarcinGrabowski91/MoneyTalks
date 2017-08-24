package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Category;

public class BudgetSummaryAdapter extends RecyclerView.Adapter<BudgetSummaryCategoryViewHolder> {

    private List<Category> categoriesList = new ArrayList<>();

    private BudgetSummaryCategoryViewHolder.Listener listener;

    public BudgetSummaryAdapter(BudgetSummaryCategoryViewHolder.Listener listener) {
        this.listener = listener;
    }

    @Override
    public BudgetSummaryCategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        return new BudgetSummaryCategoryViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(BudgetSummaryCategoryViewHolder holder, int position) {
        Category category = categoriesList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void setCategories(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
        notifyDataSetChanged();
    }
}