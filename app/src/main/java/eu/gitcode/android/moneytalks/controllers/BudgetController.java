package eu.gitcode.android.moneytalks.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.api.BudgetApi;
import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import rx.Observable;

@Singleton
public class BudgetController {
    private final BudgetApi budgetApi;

    @Inject
    public BudgetController(BudgetApi budgetApi) {
        this.budgetApi = budgetApi;
    }

    public Observable<List<CategoryRest>> getCategories() {
        return budgetApi.getCategories();
    }
}