package eu.gitcode.android.moneytalks.ui.feature.register;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.StringUtils;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class RegisterFragmentPresenter extends MvpBasePresenterRest<RegisterContract.View>
        implements RegisterContract.Presenter {

    private final AuthController authController;

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public RegisterFragmentPresenter(AuthController authController,
                                     PreferenceController preferenceController) {
        this.authController = authController;
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void registerAccount(String firstName, String lastName, String email, String password, String rePassword) {
        if (StringUtils.isAnyNullOrEmpty(firstName, lastName, email, password, rePassword)) {
            getView().showDataFillError();
        } else if (!StringUtils.isEmailValid(email)) {
            getView().emailNotValidError();
        } else if (password.length() < 6) {
            getView().showPasswordTooShortError();
        } else if (!password.equals(rePassword)) {
            getView().showPasswordsAreDifferentError();
        } else {
            getView().showRegisterSuccessView();
        }
    }
}
