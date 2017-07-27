package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.BudgetController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class SubcategoriesFragmentPresenter extends MvpBasePresenterRest<SubcategoriesContract.View>
        implements SubcategoriesContract.Presenter {

    private final BudgetController budgetController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private Long categoryId;

    @Inject
    public SubcategoriesFragmentPresenter(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void saveCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void loadSubcategories() {
        subscriptions.add(budgetController.getCategories()
                .compose(RxTransformers.applySchedulers())
                .map(Category::fromRest).subscribe(categories -> {
                    for (Category category : categories) {
                        //noinspection ConstantConditions
                        if (category.id().equals(categoryId)) {
                            getView().showSubcategoriesList(category.subcategories());
                            break;
                        }
                    }
                }, throwable -> Timber.d("Loading subcategories failed")));
    }

    @Override
    public void handleRemoveSubcategory(Subcategory subcategory) {
        getView().showRemoveSuccessView();
    }

    @Override
    public void handleAddSubcategory(String title) {
        //   getView().showSubcategoriesData();
    }

    @Override
    public void handleUpdateSubcategory(Subcategory subcategory) {
        //   getView().showSubcategoriesData();
    }
}
