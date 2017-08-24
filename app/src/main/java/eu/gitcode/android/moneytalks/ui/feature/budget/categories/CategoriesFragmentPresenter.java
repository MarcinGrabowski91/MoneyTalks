package eu.gitcode.android.moneytalks.ui.feature.budget.categories;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.BudgetController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class CategoriesFragmentPresenter extends MvpBasePresenterRest<CategoriesContract.View>
        implements CategoriesContract.Presenter {

    private final BudgetController budgetController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public CategoriesFragmentPresenter(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void loadCategoriesData() {
        subscriptions.add(budgetController.getCategories()
                .compose(RxTransformers.applySchedulers())
                .map(Category::fromRest)
                .subscribe(categories -> getView().showCategoriesData(categories),
                        throwable -> Timber.d("Loading categories failed: %s", throwable)));
    }

    @Override
    public void handleRemoveCategory(Category category) {
        getView().showRemoveSuccessView();
    }

    @Override
    public void handleAddCategory(String title) {
        budgetController.addCategory(title)
                .andThen(budgetController.getCategories())
                .map(Category::fromRest)
                .compose(RxTransformers.applySchedulers())
                .subscribe(categories -> getView().showCategoriesData(categories),
                        throwable -> Timber.d("Adding category failed"));
    }

    @Override
    public void handleUpdateCategory(Category category) {
        // getView().showCategoriesData();
    }
}
