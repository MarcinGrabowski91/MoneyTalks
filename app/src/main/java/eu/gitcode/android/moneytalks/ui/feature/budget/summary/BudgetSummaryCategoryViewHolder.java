package eu.gitcode.android.moneytalks.ui.feature.budget.summary;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;

public class BudgetSummaryCategoryViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.name_txt)
    TextView nameTxt;

    @BindView(R.id.spent_txt)
    TextView spentTxt;

    @BindView(R.id.percent_txt)
    TextView percentTxt;

    private Category category;
    private Listener listener;

    public BudgetSummaryCategoryViewHolder(ViewGroup parent,
                                           Listener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_category_view_holder, parent, false));
        this.listener = listener;
    }

    @OnClick(R.id.card_view)
    void onCardViewClicked() {
        listener.onCategoryClicked(category);
    }

    @Override
    public void bind(Category item) {
        category = item;
        nameTxt.setText(item.name());
        if (item.budgeted() == null) {
            spentTxt.setText(String.format(itemView.getResources().getString(R.string.currency_amount), 0f));
        } else {
            spentTxt.setText(String.format(itemView.getResources().getString(R.string.currency_amount), item.budgeted()));
            percentTxt.setText(String.format(itemView.getResources().getString(R.string.percent_amount), item.percentPerMonth()));
        }
    }

    public interface Listener {
        void onCategoryClicked(Category category);
    }
}