package eu.gitcode.android.moneytalks.ui.feature.register;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

interface RegisterContract {
    interface View extends MvpViewRest {

        void showEmailTakenError();

        void showDataFillError();

        void showRegisterSuccessView();

        void showPasswordsAreDifferentError();

        void showPasswordTooShortError();

        void showEmailNotValidError();

        void showRegistrationFailedError();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void registerAccount(String username, String email, String password, String rePassword);
    }
}