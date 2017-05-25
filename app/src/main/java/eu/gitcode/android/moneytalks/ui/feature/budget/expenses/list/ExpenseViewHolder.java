package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show.ExpenseActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class ExpenseViewHolder extends BaseViewHolder<Expense> {

    @BindView(R.id.name_txt)
    TextView nameTxt;

    @BindView(R.id.description_txt)
    TextView descriptionTxt;

    @BindView(R.id.spent_txt)
    TextView spentTxt;

    @BindView(R.id.date_txt)
    TextView dateTxt;

    private Expense expense;

    private ExpenseViewHolderListener listener;

    public ExpenseViewHolder(ViewGroup parent, ExpenseViewHolderListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_view_holder, parent, false));
        this.listener = listener;
    }

    @OnClick(R.id.card_view)
    void onCardClick() {
        ExpenseActivity.startActivity(itemView.getContext(), expense);
    }

    @OnLongClick(R.id.card_view)
    boolean onCardLongClick() {
        listener.onExpenseLongClicked(expense);
        return true;
    }

    @Override
    public void bind(Expense item) {
        this.expense = item;
        nameTxt.setText(item.title());
        descriptionTxt.setText(item.description());
        spentTxt.setText(String.format(itemView.getResources().getString(R.string.currency_amount), item.cost()));
        dateTxt.setText(DateUtils.getShortDateStringFromDateTime(item.date()));
    }

    public interface ExpenseViewHolderListener {
        void onExpenseLongClicked(Expense expense);
    }

}
