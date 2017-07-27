package eu.gitcode.android.moneytalks.api;

import java.util.List;

import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import retrofit2.http.GET;
import rx.Observable;

public interface BudgetApi {
    @GET("budget/categories/")
    Observable<List<CategoryRest>> getCategories();
}