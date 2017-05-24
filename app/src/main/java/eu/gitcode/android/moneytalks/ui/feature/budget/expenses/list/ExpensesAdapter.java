package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Expense;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    private List<Expense> expensesList = new ArrayList<>();

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        return new ExpenseViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Expense expense = expensesList.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public void setExpenses(List<Expense> expensesList) {
        this.expensesList = expensesList;
        notifyDataSetChanged();
    }
}