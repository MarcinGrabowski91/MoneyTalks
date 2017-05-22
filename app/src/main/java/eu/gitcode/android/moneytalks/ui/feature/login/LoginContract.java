package eu.gitcode.android.moneytalks.ui.feature.login;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface LoginContract {
    interface View extends MvpViewRest {
        void showWrongCredentialsError();

        void showMainView();

        void showDataFillError();

        void emailNotValidError();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void loginWithCredentials(String email, String password);
    }
}