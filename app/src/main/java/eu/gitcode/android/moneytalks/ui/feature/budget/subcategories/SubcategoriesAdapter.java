package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Subcategory;

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesViewHolder> {

    private SubcategoriesViewHolder.SubcategoryViewHolderListener listener;
    private List<Subcategory> subcategoriesList = new ArrayList<>();

    public SubcategoriesAdapter(SubcategoriesViewHolder.SubcategoryViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public SubcategoriesViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        return new SubcategoriesViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(SubcategoriesViewHolder holder, int position) {
        Subcategory subcategory = subcategoriesList.get(position);
        holder.bind(subcategory);
    }

    @Override
    public int getItemCount() {
        return subcategoriesList.size();
    }

    public void setSubcategories(List<Subcategory> subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
        notifyDataSetChanged();
    }
}