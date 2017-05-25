package eu.gitcode.android.moneytalks.ui.feature.notifications.messages;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface MessagesContract {
    interface View extends MvpViewRest {
        void showMessagesData();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleMessagesData();
    }
}