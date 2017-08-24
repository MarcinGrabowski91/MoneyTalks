package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Months;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Budget;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseActivity;
import eu.gitcode.android.moneytalks.ui.feature.budget.subcategories.SubcategoriesActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class BudgetSummaryFragment extends BaseMvpFragment<BudgetSummaryContract.View,
        BudgetSummaryContract.Presenter> implements BudgetSummaryContract.View {
    public static final String TAG = BudgetSummaryFragment.class.getSimpleName();

    public static final int YEAR_RANGE = 100;

    @BindView(R.id.planned_budget_txt)
    TextView plannedBudgetTxt;

    @BindView(R.id.spent_budget_txt)
    TextView spentBudgetTxt;

    @BindView(R.id.summary_budget_txt)
    TextView summaryBudgetTxt;

    @BindView(R.id.date_txt)
    TextView dateTxt;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DateTime lastChosenDateTime = DateTime.now();

    BudgetSummaryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.budget_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showCurrentDate();
        setupRecyclerView();
        getPresenter().handleBudgetData(0);
    }

    @OnClick(R.id.floating_btn)
    void onFloatingBtnClick() {
        AddEditExpenseActivity.startActivityForResult(this);
    }

    @OnClick(R.id.date_txt)
    void onMonthBtnClicked() {
        View pickersView = getActivity().getLayoutInflater()
                .inflate(R.layout.date_picker, null);
        NumberPicker monthPicker = pickersView.findViewById(R.id.month_picker);
        monthPicker.setDisplayedValues(getResources().getStringArray(R.array.months));
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(11);
        monthPicker.setValue(lastChosenDateTime.getMonthOfYear() - 1);
        NumberPicker yearPicker = pickersView.findViewById(R.id.year_picker);
        yearPicker.setMinValue(DateTime.now().getYear() - YEAR_RANGE);
        yearPicker.setMaxValue(DateTime.now().getYear() + YEAR_RANGE);
        yearPicker.setValue(lastChosenDateTime.getYear());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(pickersView)
                .setPositiveButton(R.string.ok, (dialog, position) -> {
                    int year = yearPicker.getValue();
                    int month = monthPicker.getValue() + 1;
                    int day = DateTime.now().getDayOfMonth();
                    lastChosenDateTime = new DateTime(year, month, day, 0, 0);
                    String monthWithYear = DateUtils.getMonthWithYear(lastChosenDateTime);
                    dateTxt.setText(monthWithYear);
                    int monthsDifference = Months.monthsBetween(DateTime.now().withDayOfMonth(1),
                            lastChosenDateTime.withDayOfMonth(1)).getMonths();
                    getPresenter().handleBudgetData(monthsDifference);
                })
                .setNegativeButton(R.string.cancel, (dialog, position) -> {
                })
                .create().show();
    }

    @Override
    @NonNull
    public BudgetSummaryFragmentPresenter createPresenter() {
        BudgetSummaryComponent component =
                App.getAppComponent(getContext()).getBudgetSummaryComponent();
        component.inject(this);
        return component.getBudgetSummaryFragmentPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showBudgetData(Budget budget) {
        //TODO load budget data from the server
        plannedBudgetTxt.setText(String.format(getString(R.string.currency_amount), 2500f));
        spentBudgetTxt.setText(String.format(getString(R.string.currency_amount), budget.value()));
        summaryBudgetTxt.setText(String.format(getString(R.string.currency_amount), budget.value()));
        summaryBudgetTxt.setEnabled(2500 - budget.value() >= 0);
        adapter.setCategories(budget.categoriesList());
    }

    private void showCurrentDate() {
        dateTxt.setText(DateUtils.getCurrentMonthWithYear());
    }

    private void setupRecyclerView() {
        BudgetSummaryCategoryViewHolder.Listener listener =
                category -> SubcategoriesActivity.startActivity(getContext(), category.name(),
                        category.id(), category.subcategoriesMonth(), true);
        adapter = new BudgetSummaryAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}