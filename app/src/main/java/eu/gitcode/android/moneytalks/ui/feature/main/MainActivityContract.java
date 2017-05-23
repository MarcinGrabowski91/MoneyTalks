package eu.gitcode.android.moneytalks.ui.feature.main;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface MainActivityContract {
    interface View extends MvpViewRest {
        void showUserNavigationDrawerFullName(String fullName);

        void showLoginView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void logout();

        void loadUserNavigationHeaderData();
    }
}