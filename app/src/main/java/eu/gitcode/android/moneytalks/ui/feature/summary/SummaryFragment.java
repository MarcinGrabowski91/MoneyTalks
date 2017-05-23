package eu.gitcode.android.moneytalks.ui.feature.summary;

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
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;

public class SummaryFragment extends BaseMvpFragment<SummaryContract.View, SummaryContract.Presenter>
        implements SummaryContract.View {
    public static final String TAG = SummaryFragment.class.getSimpleName();

    @BindView(R.id.planned_budget_txt)
    TextView plannedBudgetTxt;

    @BindView(R.id.spent_budget_txt)
    TextView spentBudgetTxt;

    @BindView(R.id.summary_budget_txt)
    TextView summaryBudgetTxt;

    @BindView(R.id.bought_investments_txt)
    TextView boughtInvestmentsTxt;

    @BindView(R.id.sell_investments_txt)
    TextView sellInvestmentsTxt;

    @BindView(R.id.investment_profit_txt)
    TextView investmentsProfit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.summary_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().handleSummaryData();
    }

    @Override
    @NonNull
    public SummaryFragmentPresenter createPresenter() {
        SummaryComponent component = App.getAppComponent(getContext()).getSummaryComponent();
        component.inject(this);
        return component.getSummaryFragmentPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showSummaryData() {
        //TODO load summary data from server
        plannedBudgetTxt.setText(String.format(getString(R.string.currency_amount), 2500));
        spentBudgetTxt.setText(String.format(getString(R.string.currency_amount), 1000));
        summaryBudgetTxt.setText(String.format(getString(R.string.currency_amount), 1500));
        boughtInvestmentsTxt.setText(String.format(getString(R.string.currency_amount), 2500));
        sellInvestmentsTxt.setText(String.format(getString(R.string.currency_amount), 1200));
        investmentsProfit.setText(String.format(getString(R.string.percent_amount), -12.4));
        summaryBudgetTxt.setEnabled(true);
        investmentsProfit.setEnabled(false);
    }
}