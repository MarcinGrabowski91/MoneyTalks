package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.gitcode.android.moneytalks.models.api.CategoryRest;

@AutoValue
public abstract class Category implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Category.Builder();
    }

    public static Category fromRest(CategoryRest categoryRest) {
        return Category.builder()
                .id(categoryRest.id())
                .subcategories(Subcategory.fromRest(categoryRest.subcategoriesRest()))
                .subcategoriesMonth(Subcategory.fromRest(categoryRest.subcategoriesRestMonth()))
                .name(categoryRest.name())
                .budgetId(categoryRest.budgetId())
                .build();
    }

    public static List<Category> fromRest(List<CategoryRest> categoriesRestList) {
        if (categoriesRestList != null) {
            List<Category> categoriesList = new ArrayList<>(categoriesRestList.size());
            for (CategoryRest categoryRest : categoriesRestList) {
                categoriesList.add(fromRest(categoryRest));
            }
            return categoriesList;
        }
        return Collections.emptyList();
    }

    @Nullable
    public abstract Long id();

    @Nullable
    public abstract List<Subcategory> subcategories();

    @Nullable
    public abstract List<Subcategory> subcategoriesMonth();

    public abstract String name();

    @Nullable
    public abstract Long budgetId();

    @Nullable
    public abstract Float budgeted();

    @Nullable
    public abstract Float percentPerMonth();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder subcategories(List<Subcategory> subcategories);

        public abstract Builder subcategoriesMonth(List<Subcategory> subcategoriesMonth);

        public abstract Builder name(String name);

        public abstract Builder budgetId(Long budgetId);

        public abstract Builder budgeted(Float budgeted);

        public abstract Builder percentPerMonth(Float percentPerMonth);

        public abstract Category build();
    }
}