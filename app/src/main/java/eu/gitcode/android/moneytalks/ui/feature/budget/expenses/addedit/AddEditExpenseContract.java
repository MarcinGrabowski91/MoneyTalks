package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import org.joda.time.DateTime;

import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface AddEditExpenseContract {
    interface View extends MvpViewRest {
        void showExpenseData(Transaction transaction);

        void showAddOrUpdateSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleExpenseData(Transaction transaction);

        void addOrUpdateExpense(String title, DateTime date, Float value);

        void saveSubcategoryId(Long subcategoryId);
    }
}