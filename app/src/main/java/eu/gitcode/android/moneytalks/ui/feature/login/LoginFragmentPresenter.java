package eu.gitcode.android.moneytalks.ui.feature.login;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.LoginController;
import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.StringUtils;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class LoginFragmentPresenter extends MvpBasePresenterRest<LoginContract.View>
        implements LoginContract.Presenter {

    private final LoginController loginController;

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public LoginFragmentPresenter(LoginController loginController,
                                  PreferenceController preferenceController) {
        this.loginController = loginController;
        this.preferenceController = preferenceController;
    }

    @Override
    public void loginWithCredentials(String email, String password) {
        if (StringUtils.isAnyNullOrEmpty(email, password)) {
            getView().showDataFillError();
        } else if (!StringUtils.isEmailValid(email)) {
            getView().emailNotValidError();
        } else {
            getView().showMainView();
        }
    }

    public void clearSharedPreferences() {
        preferenceController.clearSharedPreferences();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }
}