package eu.gitcode.android.moneytalks.models.ui;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

import java.util.List;

import eu.gitcode.android.moneytalks.models.api.BudgetRest;

@AutoValue
public abstract class Budget {

    public static Budget fromRest(BudgetRest budgetRest) {
        return Budget.builder()
                .id(budgetRest.id())
                .date(budgetRest.date())
                .userId(budgetRest.userId())
                .categoriesList(Category.fromRest(budgetRest.categoriesRestList()))
                .build();
    }

    public static Budget fromRest(BudgetRest budgetRest, Float value) {
        return Budget.builder()
                .id(budgetRest.id())
                .date(budgetRest.date())
                .userId(budgetRest.userId())
                .categoriesList(Category.fromRest(budgetRest.categoriesRestList()))
                .value(value)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_Budget.Builder();
    }

    public abstract Long id();

    public abstract DateTime date();

    public abstract Long userId();

    public abstract List<Category> categoriesList();

    public abstract Float value();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder date(DateTime date);

        public abstract Builder userId(Long userId);

        public abstract Builder categoriesList(List<Category> categoriesList);

        public abstract Builder value(Float value);

        public abstract Budget build();
    }
}