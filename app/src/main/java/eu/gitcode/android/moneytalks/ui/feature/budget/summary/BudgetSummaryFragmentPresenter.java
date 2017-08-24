package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.BudgetController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.api.CategoryRest;
import eu.gitcode.android.moneytalks.models.api.SubcategoryRest;
import eu.gitcode.android.moneytalks.models.ui.Budget;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class BudgetSummaryFragmentPresenter extends MvpBasePresenterRest<BudgetSummaryContract.View>
        implements BudgetSummaryContract.Presenter {

    private final BudgetController budgetController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public BudgetSummaryFragmentPresenter(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleBudgetData(int monthDifference) {
        subscriptions.add(budgetController.getBudget(monthDifference)
                .map(budgetRest -> {
                    Float totalBudgeted = 0f;
                    for (CategoryRest categoryRest : budgetRest.categoriesRestList()) {
                        if (categoryRest.subcategoriesRest() != null) {
                            for (SubcategoryRest subcategoryRest : categoryRest.subcategoriesRest()) {
                                totalBudgeted = subcategoryRest.budgeted();
                            }
                        }
                    }
                    return Budget.fromRest(budgetRest, totalBudgeted);
                })
                .compose(RxTransformers.applySchedulers())
                .subscribe(budget -> getView().showBudgetData(budget),
                        throwable -> Timber.d("Error during loading budget, %s",
                                throwable.getLocalizedMessage())));
    }
}
