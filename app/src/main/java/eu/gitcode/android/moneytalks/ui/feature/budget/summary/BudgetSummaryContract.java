package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import eu.gitcode.android.moneytalks.models.ui.Budget;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface BudgetSummaryContract {
    interface View extends MvpViewRest {
        void showBudgetData(Budget budget);
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleBudgetData(int monthDifference);
    }
}