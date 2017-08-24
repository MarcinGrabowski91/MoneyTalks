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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.enumeration.ItemActionChooserEnum;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseActivity;

import static eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list.ExpensesActivity.TRANSACTIONS_LIST;

public class ExpensesFragment extends BaseMvpFragment<ExpensesContract.View,
        ExpensesContract.Presenter> implements ExpensesContract.View {
    public static final String TAG = ExpensesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ExpensesAdapter adapter;

    public static ExpensesFragment newInstance(List<Transaction> transactionsList) {
        ExpensesFragment subcategoriesFragment = new ExpensesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(TRANSACTIONS_LIST, new ArrayList<>(transactionsList));
        subcategoriesFragment.setArguments(args);

        return subcategoriesFragment;
    }

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
        adapter.setExpenses(getArguments().getParcelableArrayList(TRANSACTIONS_LIST));
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().handleBudgetData();
    }

    private void setupRecyclerView() {
        ExpenseViewHolder.ExpenseViewHolderListener listener = transaction ->
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        AddEditExpenseActivity.startActivityForResult(this, transaction);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveExpenseDialog(transaction);
                                    }
                                }).show();
        adapter = new ExpensesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showRemoveExpenseDialog(Transaction transaction) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_expense)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveExpense(transaction))
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}