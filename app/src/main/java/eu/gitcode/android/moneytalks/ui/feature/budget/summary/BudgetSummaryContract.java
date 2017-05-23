package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface BudgetSummaryContract {
    interface View extends MvpViewRest {
        void showBudgetData();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleBudgetData();
    }
}