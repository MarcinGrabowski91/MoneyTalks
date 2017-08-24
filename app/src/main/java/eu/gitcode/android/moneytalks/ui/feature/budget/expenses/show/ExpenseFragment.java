package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;

import static eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show.ExpenseActivity.TRANSACTION;

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

    public static ExpenseFragment newInstance(Transaction expense) {
        ExpenseFragment expenseFragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putParcelable(TRANSACTION, expense);
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
            getPresenter().handleExpenseData(getArguments().getParcelable(TRANSACTION));
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
    public void showExpenseData(Transaction transaction) {
        titleTxt.setText(transaction.name());
        dateTxt.setText(DateUtils.getLongDateStringFromDateTime(transaction.date()));
        costTxt.setText(String.format(getString(R.string.currency_amount), transaction.value()));
    }

    @Override
    public void showRemoveSuccessView() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    public void handleEditExpense() {
        AddEditExpenseActivity.startActivityForResult(this, getPresenter().getExpense());
    }

    public void handleRemoveExpense() {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_expense)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveExpense())
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}