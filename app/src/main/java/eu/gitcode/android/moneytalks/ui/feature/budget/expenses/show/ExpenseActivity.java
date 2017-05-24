package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Expense;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class ExpenseActivity extends BaseActivity {

    public static final String EXPENSE = "EXPENSE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivity(Context context, Expense expense) {
        Intent intent = new Intent(context, ExpenseActivity.class);
        intent.putExtra(EXPENSE, expense);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null && getIntent().hasExtra(EXPENSE)) {
            Expense expense = getIntent().getParcelableExtra(EXPENSE);
            toolbar.setTitle(expense.title());
            replaceFragment(R.id.fragment_container, ExpenseFragment.newInstance(expense),
                    ExpenseFragment.TAG).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ExpenseFragment expenseFragment =
                (ExpenseFragment) getSupportFragmentManager()
                        .findFragmentByTag(ExpenseFragment.TAG);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_menu_btn:
                if (expenseFragment != null) {
                    expenseFragment.handleEditExpense();
                }
                break;
            case R.id.remove_menu_btn:
                if (expenseFragment != null) {
                    expenseFragment.handleRemoveExpense();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
