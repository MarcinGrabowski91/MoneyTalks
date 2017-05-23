package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class BudgetSummaryFragment extends BaseMvpFragment<BudgetSummaryContract.View,
        BudgetSummaryContract.Presenter> implements BudgetSummaryContract.View {
    public static final String TAG = BudgetSummaryFragment.class.getSimpleName();

    @BindView(R.id.planned_budget_txt)
    TextView plannedBudgetTxt;

    @BindView(R.id.spent_budget_txt)
    TextView spentBudgetTxt;

    @BindView(R.id.summary_budget_txt)
    TextView summaryBudgetTxt;

    @BindView(R.id.month_txt)
    TextView monthTxt;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    BudgetSummaryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.budget_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showCurrentMonth();
        setupRecyclerView();
        getPresenter().handleBudgetData();
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
    public void showBudgetData() {
        //TODO load budget data from the server
        plannedBudgetTxt.setText(String.format(getString(R.string.currency_amount), 2500));
        spentBudgetTxt.setText(String.format(getString(R.string.currency_amount), 1000));
        summaryBudgetTxt.setText(String.format(getString(R.string.currency_amount), 1500));
        summaryBudgetTxt.setEnabled(true);
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(Category.builder().name("Clothes").build());
        categoriesList.add(Category.builder().name("Additional electronic devices").build());
        categoriesList.add(Category.builder().name("Others").build());
        categoriesList.add(Category.builder().name("Clothes").build());
        categoriesList.add(Category.builder().name("Additional electronic devices").build());
        categoriesList.add(Category.builder().name("Others").build());
        categoriesList.add(Category.builder().name("Clothes").build());
        categoriesList.add(Category.builder().name("Additional electronic devices").build());
        categoriesList.add(Category.builder().name("Others").build());
        adapter.setCategories(categoriesList);
    }

    private void showCurrentMonth() {
        monthTxt.setText(DateUtils.getCurrentMonth());
    }

    private void setupRecyclerView() {
        adapter = new BudgetSummaryAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}