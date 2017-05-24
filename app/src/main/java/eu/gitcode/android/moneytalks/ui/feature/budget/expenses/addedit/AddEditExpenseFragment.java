package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

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

import static android.app.Activity.RESULT_OK;

public class AddEditExpenseFragment extends BaseMvpFragment<AddEditExpenseContract.View,
        AddEditExpenseContract.Presenter> implements AddEditExpenseContract.View {
    public static final String TAG = AddEditExpenseFragment.class.getSimpleName();

    @BindView(R.id.title_edit)
    TextView titleEdit;
    @BindView(R.id.date_edit)
    TextView dateEdit;
    @BindView(R.id.cost_edit)
    TextView costEdit;
    @BindView(R.id.category_edit)
    TextView categoryEdit;
    @BindView(R.id.description_edit)
    TextView descriptionEdit;

    public static AddEditExpenseFragment newInstance(Expense expense) {
        AddEditExpenseFragment addEditExpenseFragment = new AddEditExpenseFragment();
        Bundle args = new Bundle();
        args.putParcelable(AddEditExpenseActivity.EXPENSE, expense);
        addEditExpenseFragment.setArguments(args);

        return addEditExpenseFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_edit_expense_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            getPresenter().handleExpenseData(getArguments()
                    .getParcelable(AddEditExpenseActivity.EXPENSE));
        }
    }

    @Override
    @NonNull
    public AddEditExpenseFragmentPresenter createPresenter() {
        AddEditExpenseComponent component = App.getAppComponent(getContext())
                .getAddEditExpenseComponent();
        component.inject(this);
        return component.getAddEditExpensePresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showExpenseData(Expense expense) {
        titleEdit.setText(expense.title());
        dateEdit.setText(DateUtils.getLongDateStringFromDateTime(expense.date()));
        costEdit.setText(String.format(getString(R.string.currency_amount), expense.cost()));
        if (expense.category() != null) {
            //noinspection ConstantConditions
            categoryEdit.setText(expense.category().name());
        }
        descriptionEdit.setText(expense.description());
    }

    @Override
    public void showAddOrUpdateSuccessView() {
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

    public void addOrUpdateExpense() {
        getPresenter().addOrUpdateExpense();
    }
}