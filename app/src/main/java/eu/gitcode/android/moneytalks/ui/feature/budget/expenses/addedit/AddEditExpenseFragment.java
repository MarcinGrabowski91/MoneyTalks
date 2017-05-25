package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.categories.list.CategoriesActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;
import onactivityresult.ActivityResult;
import onactivityresult.OnActivityResult;

import static android.app.Activity.RESULT_OK;

public class AddEditExpenseFragment extends BaseMvpFragment<AddEditExpenseContract.View,
        AddEditExpenseContract.Presenter> implements AddEditExpenseContract.View,
        DatePickerDialog.OnDateSetListener {
    public static final String TAG = AddEditExpenseFragment.class.getSimpleName();

    @BindView(R.id.title_edit)
    TextInputEditText titleEdit;
    @BindView(R.id.date_edit)
    TextInputEditText dateEdit;
    @BindView(R.id.cost_edit)
    TextInputEditText costEdit;
    @BindView(R.id.category_edit)
    TextInputEditText categoryEdit;
    @BindView(R.id.description_edit)
    TextInputEditText descriptionEdit;

    DatePickerDialog datePickerDialog;

    DateTime dateTime = DateTime.now();

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
        if (getArguments() != null && getArguments().containsKey(AddEditExpenseActivity.EXPENSE)) {
            Expense expense = getArguments().getParcelable(AddEditExpenseActivity.EXPENSE);
            if (expense != null) {
                dateTime = expense.date();
                getPresenter().handleExpenseData(expense);
            }
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

    @OnClick(R.id.date_edit)
    void onDateClick() {
        showDatePickerDialog();
    }

    @OnClick(R.id.category_edit)
    void onCategoryClick() {
        CategoriesActivity.startActivityForResult(this, null);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateTime = new DateTime(year, month, dayOfMonth, 0, 0);
        dateEdit.setText(DateUtils.getLongDateStringFromDateTime(dateTime));
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = CategoriesActivity.CATEGORY_REQUEST)
    void onActivityResultRegisterSuccess(final int resultCode, Intent data) {
        if (RESULT_OK == resultCode && data.hasExtra(CategoriesActivity.CATEGORY)) {
            Category category = data.getParcelableExtra(CategoriesActivity.CATEGORY);
            categoryEdit.setText(category.name());
        }
    }

    public void addOrUpdateExpense() {
        getPresenter().addOrUpdateExpense();
    }

    private void showDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                getContext(), this, dateTime.getYear(), dateTime.getMonthOfYear() - 1,
                dateTime.getDayOfMonth());
        datePickerDialog.show();
    }


}