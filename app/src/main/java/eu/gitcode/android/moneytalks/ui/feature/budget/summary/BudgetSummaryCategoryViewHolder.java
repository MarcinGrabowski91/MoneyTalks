package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list.ExpensesActivity;

public class BudgetSummaryCategoryViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.name_txt)
    TextView nameTxt;

    @BindView(R.id.spent_txt)
    TextView spentTxt;

    @BindView(R.id.percent_txt)
    TextView percentTxt;

    public BudgetSummaryCategoryViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_category_view_holder, parent, false));
    }

    @OnClick(R.id.card_view)
    void onCardViewClicked() {
        ExpensesActivity.startActivity(itemView.getContext(), nameTxt.getText().toString());
    }

    @Override
    public void bind(Category item) {
        //TODO load real data
        nameTxt.setText(item.name());
        spentTxt.setText(String.format(itemView.getResources().getString(R.string.currency_amount), 1200f));
        percentTxt.setText(String.format(itemView.getResources().getString(R.string.percent_amount), 23.0));
    }
}
