package eu.gitcode.android.moneytalks.ui.feature.summary;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface SummaryContract {
    interface View extends MvpViewRest {
        void showSummaryData();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleSummaryData();
    }
}