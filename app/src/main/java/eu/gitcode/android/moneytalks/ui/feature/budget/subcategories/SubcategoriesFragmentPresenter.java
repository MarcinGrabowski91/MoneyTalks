package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class SubcategoriesFragmentPresenter extends MvpBasePresenterRest<SubcategoriesContract.View>
        implements SubcategoriesContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public SubcategoriesFragmentPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleCategoriesData() {
        getView().showSubcategoriesData();
    }

    @Override
    public void handleRemoveSubcategory(Subcategory subcategory) {
        getView().showRemoveSuccessView();
    }

    @Override
    public void handleAddSubcategory(String title) {
        getView().showSubcategoriesData();
    }

    @Override
    public void handleUpdateSubcategory(Subcategory subcategory) {
        getView().showSubcategoriesData();
    }
}
