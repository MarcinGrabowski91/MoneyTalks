package eu.gitcode.android.moneytalks.ui.feature.register;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import eu.gitcode.android.moneytalks.utils.StringUtils;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class RegisterFragmentPresenter extends MvpBasePresenterRest<RegisterContract.View>
        implements RegisterContract.Presenter {

    private final AuthController authController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public RegisterFragmentPresenter(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void registerAccount(String username, String email, String password, String rePassword) {
        if (StringUtils.isAnyNullOrEmpty(username, email, password, rePassword)) {
            getView().showDataFillError();
        } else if (!StringUtils.isEmailValid(email)) {
            getView().showEmailNotValidError();
        } else if (password.length() < 6) {
            getView().showPasswordTooShortError();
        } else if (!password.equals(rePassword)) {
            getView().showPasswordsAreDifferentError();
        } else {
            subscriptions.add(authController.register(username, email, password)
                    .compose(RxTransformers.applyCompletableSchedulers())
                    .subscribe(() -> getView().showRegisterSuccessView(),
                            throwable -> {
                                Timber.d("Register failed", throwable.getLocalizedMessage());
                                getView().showRegistrationFailedError();
                            }));
            getView().showRegisterSuccessView();
        }
    }
}
