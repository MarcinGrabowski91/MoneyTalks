package eu.gitcode.android.moneytalks.ui.feature.main;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.ActivityScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public final class MainActivityPresenter extends MvpBasePresenterRest<MainActivityContract.View>
        implements MainActivityContract.Presenter {

    private final PreferenceController preferenceController;
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public MainActivityPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void logout() {
        preferenceController.clearSharedPreferences();
        getView().showLoginView();
    }

    @Override
    public void loadUserNavigationHeaderData() {
        getView().showUserNavigationDrawerFullName("Albert Rokicki");
    }
}