package eu.gitcode.android.moneytalks.api;

import java.util.List;

import eu.gitcode.android.moneytalks.models.api.BudgetRest;
import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import eu.gitcode.android.moneytalks.models.request.CategoryRequest;
import eu.gitcode.android.moneytalks.models.request.SubcategoryRequest;
import eu.gitcode.android.moneytalks.models.request.TransactionRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Completable;
import rx.Observable;

public interface BudgetApi {
    @GET("budget/categories/")
    Observable<List<CategoryRest>> getCategories();

    @POST("budget/createNewCategoryTemplate/")
    Completable addCategory(@Body CategoryRequest categoryRequest);

    @POST("budget/createNewSubCategoryTemplate/")
        //TODO something is wrong
    Completable addSubcategory(@Body SubcategoryRequest categoryRequest);

    @GET("budget/getBudget/{monthDifference}")
    Observable<BudgetRest> getBudget(@Path("monthDifference") int monthDifference);

    @POST("budget/newTransaction/")
    Completable newTransaction(@Body TransactionRequest transactionRequest);
}