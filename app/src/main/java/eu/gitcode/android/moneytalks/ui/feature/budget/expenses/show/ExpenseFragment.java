package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class ExpenseFragment extends BaseMvpFragment<ExpenseContract.View,
        ExpenseContract.Presenter> implements ExpenseContract.View {
    public static final String TAG = ExpenseFragment.class.getSimpleName();

    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.date_txt)
    TextView dateTxt;
    @BindView(R.id.cost_txt)
    TextView costTxt;
    @BindView(R.id.category_title_txt)
    TextView categoryTitleTxt;
    @BindView(R.id.category_txt)
    TextView categoryTxt;
    @BindView(R.id.description_title_txt)
    TextView descriptionTitle;
    @BindView(R.id.description_txt)
    TextView descriptionTxt;

    public static ExpenseFragment newInstance(Expense expense) {
        ExpenseFragment expenseFragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putParcelable(ExpenseActivity.EXPENSE, expense);
        expenseFragment.setArguments(args);

        return expenseFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expense_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            getPresenter().handleExpenseData(getArguments().getParcelable(ExpenseActivity.EXPENSE));
        }
    }

    @Override
    @NonNull
    public ExpenseFragmentPresenter createPresenter() {
        ExpenseComponent component = App.getAppComponent(getContext()).getExpenseComponent();
        component.inject(this);
        return component.getExpensePresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showExpenseData(Expense expense) {
        titleTxt.setText(expense.title());
        dateTxt.setText(DateUtils.getLongDateStringFromDateTime(expense.date()));
        costTxt.setText(String.format(getString(R.string.currency_amount), expense.cost()));
        if (expense.category() != null) {
            categoryTxt.setText(expense.category().name());
        }
        descriptionTxt.setText(expense.description());
    }
}