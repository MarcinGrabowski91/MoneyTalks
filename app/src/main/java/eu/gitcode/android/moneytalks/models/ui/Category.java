package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
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
                .name(categoryRest.name())
                .build();
    }

    public static List<Category> fromRest(List<CategoryRest> categoriesRestList) {
        List<Category> categoriesList = new ArrayList<>(categoriesRestList.size());
        for (CategoryRest categoryRest : categoriesRestList) {
            categoriesList.add(fromRest(categoryRest));
        }
        return categoriesList;
    }

    @Nullable
    public abstract Long id();

    @Nullable
    public abstract List<Subcategory> subcategories();

    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder subcategories(List<Subcategory> subcategories);

        public abstract Builder name(String name);

        public abstract Category build();
    }
}