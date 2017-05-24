package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;

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

    private void setupRecyclerView() {
        adapter = new ExpensesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}