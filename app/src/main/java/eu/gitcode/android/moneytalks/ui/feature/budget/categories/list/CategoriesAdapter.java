package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private CategoriesViewHolder.ExpenseViewHolderListener listener;
    private List<Category> categoriesList = new ArrayList<>();

    public CategoriesAdapter(CategoriesViewHolder.ExpenseViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        return new CategoriesViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
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