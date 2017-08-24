package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Collections;
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
                .categoryMonthId(subcategoryRest.categoryMonthId())
                .budgeted(subcategoryRest.budgeted())
                .transactionsList(Transaction.fromRest(subcategoryRest.transactionsRestList()))
                .build();
    }

    public static List<Subcategory> fromRest(List<SubcategoryRest> subcategoriesRestList) {
        if (subcategoriesRestList != null) {
            List<Subcategory> subcategoriesList = new ArrayList<>(subcategoriesRestList.size());
            for (SubcategoryRest subcategoryRest : subcategoriesRestList) {
                subcategoriesList.add(fromRest(subcategoryRest));
            }
            return subcategoriesList;
        }
        return Collections.emptyList();
    }

    @Nullable
    public abstract Long id();

    @Nullable
    public abstract Long categoryId();

    @Nullable
    public abstract Long categoryMonthId(); //TODO change to categoryId

    public abstract String name();

    @Nullable
    public abstract Float budgeted();

    @Nullable
    public abstract List<Transaction> transactionsList();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder categoryId(Long categoryId);

        public abstract Builder categoryMonthId(Long categoryMonthId);

        public abstract Builder name(String name);

        public abstract Builder budgeted(Float budgeted);

        public abstract Builder transactionsList(List<Transaction> transactionsList);

        public abstract Subcategory build();
    }
}