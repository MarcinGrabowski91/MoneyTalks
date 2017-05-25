package eu.gitcode.android.moneytalks.ui.feature.notifications.logs;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface LogsContract {
    interface View extends MvpViewRest {
        void showLogsData();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleLogsData();
    }
}