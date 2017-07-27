package eu.gitcode.android.moneytalks.ui.feature.login;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import eu.gitcode.android.moneytalks.utils.StringUtils;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class LoginFragmentPresenter extends MvpBasePresenterRest<LoginContract.View>
        implements LoginContract.Presenter {

    private final AuthController authController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public LoginFragmentPresenter(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public void loginWithCredentials(String email, String password) {
        if (StringUtils.isAnyNullOrEmpty(email, password)) {
            getView().showDataFillError();
        } else if (!StringUtils.isEmailValid(email)) {
            getView().emailNotValidError();
        } else {
            subscriptions.add(authController.login(email, password)
                    .compose(RxTransformers.applySchedulers())
                    .subscribe(tokenRest -> getView().showLoginSuccessfulView(),
                            throwable -> {
                                Timber.d("Login failed", throwable.getLocalizedMessage());
                                getView().showWrongCredentialsError();
                            }));
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }
}
