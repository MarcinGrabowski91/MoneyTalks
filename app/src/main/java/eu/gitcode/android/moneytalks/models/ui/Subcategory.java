package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.api.SubcategoryRest;

@AutoValue
public abstract class Subcategory implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Subcategory.Builder();
    }

    public static Subcategory fromRest(SubcategoryRest subcategoryRest) {
        return Subcategory.builder()
                .id(subcategoryRest.id())
                .categoryId(subcategoryRest.categoryId())
                .name(subcategoryRest.name())
                .build();
    }

    public static List<Subcategory> fromRest(List<SubcategoryRest> subcategoriesRestList) {
        List<Subcategory> subcategoriesList = new ArrayList<>(subcategoriesRestList.size());
        for (SubcategoryRest subcategoryRest : subcategoriesRestList) {
            subcategoriesList.add(fromRest(subcategoryRest));
        }
        return subcategoriesList;
    }

    @Nullable
    public abstract Long id();

    public abstract Long categoryId();

    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder categoryId(Long categoryId);

        public abstract Builder name(String name);

        public abstract Subcategory build();
    }
}