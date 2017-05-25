package eu.gitcode.android.moneytalks.ui.feature.budget.categories;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private CategoryViewHolder.CategoryViewHolderListener listener;
    private List<Category> categoriesList = new ArrayList<>();

    public CategoriesAdapter(CategoryViewHolder.CategoryViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        return new CategoryViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
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