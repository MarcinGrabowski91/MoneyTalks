package eu.gitcode.android.moneytalks.api;

import java.util.List;

import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import eu.gitcode.android.moneytalks.models.request.CategoryRequest;
import eu.gitcode.android.moneytalks.models.request.SubcategoryRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
}