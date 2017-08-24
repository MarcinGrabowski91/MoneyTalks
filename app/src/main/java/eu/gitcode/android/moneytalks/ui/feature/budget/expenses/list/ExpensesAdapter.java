package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Transaction;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    private ExpenseViewHolder.ExpenseViewHolderListener listener;
    private List<Transaction> expensesList = new ArrayList<>();

    public ExpensesAdapter(ExpenseViewHolder.ExpenseViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        return new ExpenseViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Transaction expense = expensesList.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public void setExpenses(List<Transaction> expensesList) {
        this.expensesList = expensesList;
        notifyDataSetChanged();
    }
}