package eu.gitcode.android.moneytalks.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.api.BudgetApi;
import eu.gitcode.android.moneytalks.models.api.BudgetRest;
import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import eu.gitcode.android.moneytalks.models.request.CategoryRequest;
import eu.gitcode.android.moneytalks.models.request.SubcategoryRequest;
import eu.gitcode.android.moneytalks.models.request.TransactionRequest;
import rx.Completable;
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

    public Completable addCategory(String title) {
        CategoryRequest categoryRequest = CategoryRequest.create(title);
        return budgetApi.addCategory(categoryRequest);
    }

    public Completable addSubcategory(long categoryId, String title) {
        SubcategoryRequest subcategoryRequest = SubcategoryRequest.create(categoryId, title);
        return budgetApi.addSubcategory(subcategoryRequest);
    }

    public Observable<BudgetRest> getBudget(int monthDifference) {
        return budgetApi.getBudget(monthDifference);
    }

    public Completable newTransaction(TransactionRequest transactionRequest) {
        return budgetApi.newTransaction(transactionRequest);
    }
}