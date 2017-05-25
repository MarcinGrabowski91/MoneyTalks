package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class CategoriesFragmentPresenter extends MvpBasePresenterRest<CategoriesContract.View>
        implements CategoriesContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public CategoriesFragmentPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleCategoriesData() {
        getView().showCategoriesData();
    }

    @Override
    public void handleRemoveCategory(Category category) {
        getView().showRemoveSuccessView();
    }

    @Override
    public void handleAddCategory(String title) {
        getView().showCategoriesData();
    }

    @Override
    public void handleUpdateCategory(Category category) {
        getView().showCategoriesData();
    }
}
