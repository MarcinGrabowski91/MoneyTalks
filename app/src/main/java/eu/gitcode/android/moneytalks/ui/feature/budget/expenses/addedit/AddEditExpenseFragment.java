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
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.categories.CategoriesActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;
import eu.gitcode.android.moneytalks.utils.UIUtils;
import onactivityresult.ActivityResult;
import onactivityresult.OnActivityResult;

import static android.app.Activity.RESULT_OK;
import static eu.gitcode.android.moneytalks.ui.feature.budget.categories.CategoriesActivity.SUBCATEGORY;
import static eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseActivity.TRANSACTION;

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

    public static AddEditExpenseFragment newInstance(Transaction transaction) {
        AddEditExpenseFragment addEditExpenseFragment = new AddEditExpenseFragment();
        Bundle args = new Bundle();
        args.putParcelable(TRANSACTION, transaction);
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
        if (getArguments() != null && getArguments().containsKey(TRANSACTION)) {
            Transaction transaction = getArguments().getParcelable(TRANSACTION);
            if (transaction != null) {
                dateTime = transaction.date();
                getPresenter().handleExpenseData(transaction);
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
        CategoriesActivity.startActivityForResult(this);
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showExpenseData(Transaction transaction) {
        titleEdit.setText(transaction.name());
        dateEdit.setText(DateUtils.getLongDateStringFromDateTime(transaction.date()));
        costEdit.setText(String.format(getString(R.string.currency_amount), transaction.value()));
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

    @OnActivityResult(requestCode = CategoriesActivity.SUBCATEGORY_REQUEST)
    void onActivityResultRegisterSuccess(final int resultCode, Intent data) {
        if (RESULT_OK == resultCode && data.hasExtra(SUBCATEGORY)) {
            Subcategory subcategory = data.getParcelableExtra(SUBCATEGORY);
            categoryEdit.setText(subcategory.name());
            getPresenter().saveSubcategoryId(subcategory.id());
        }
    }

    public void addOrUpdateExpense() {
        if (!UIUtils.showFillError(getContext(), titleEdit, dateEdit, costEdit, categoryEdit)) {
            getPresenter().addOrUpdateExpense(titleEdit.getText().toString(), dateTime,
                    Float.parseFloat(costEdit.getText().toString()));
        }

    }

    private void showDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                getContext(), this, dateTime.getYear(), dateTime.getMonthOfYear() - 1,
                dateTime.getDayOfMonth());
        datePickerDialog.show();
    }
}