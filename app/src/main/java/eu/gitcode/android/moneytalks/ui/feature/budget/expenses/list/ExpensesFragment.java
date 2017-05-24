package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.enumeration.ItemActionChooserEnum;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseActivity;

public class ExpensesFragment extends BaseMvpFragment<ExpensesContract.View,
        ExpensesContract.Presenter> implements ExpensesContract.View {
    public static final String TAG = ExpensesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ExpensesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expenses_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().handleBudgetData();
    }

    @Override
    @NonNull
    public ExpensesFragmentPresenter createPresenter() {
        ExpensesComponent component = App.getAppComponent(getContext()).getExpensesComponent();
        component.inject(this);
        return component.getExpensesPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showExpensesData() {
        //TODO load expenses data from the server
        List<Expense> expensesList = new ArrayList<>();
        expensesList.add(Expense.builder().title("Computer").cost(5200f).description("New computer")
                .category(Category.builder().name("Inne").build()).date(DateTime.now()).build());
        expensesList.add(Expense.builder().title("Camera").cost(1400.24f).date(DateTime.now())
                .description("What a camera!").category(Category.builder().name("Takie owakie")
                        .build()).build());
        expensesList.add(Expense.builder().title("T-shirt").cost(42f).date(DateTime.now())
                .category(Category.builder().name("Ubrania").build())
                .description("Wonderful t-shirt").build());
        expensesList.add(Expense.builder().title("Hat").cost(244f).description("What a style")
                .date(DateTime.now()).category(Category.builder().name("Ubrania").build()).build());
        adapter.setExpenses(expensesList);
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().handleBudgetData();
    }

    private void setupRecyclerView() {
        ExpenseViewHolder.ExpenseViewHolderListener listener = expense ->
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        AddEditExpenseActivity.startActivityForResult(this, expense);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveExpenseDialog(expense);
                                    }
                                }).show();
        adapter = new ExpensesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showRemoveExpenseDialog(Expense expense) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_expense)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveExpense(expense))
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}