package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class ExpenseActivity extends BaseActivity {

    public static final String TRANSACTION = "transaction";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivity(Context context, Transaction transaction) {
        Intent intent = new Intent(context, ExpenseActivity.class);
        intent.putExtra(TRANSACTION, transaction);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);

        if (savedInstanceState == null && getIntent().hasExtra(TRANSACTION)) {
            Transaction expense = getIntent().getParcelableExtra(TRANSACTION);
            setUpToolbar(expense.name());
            replaceFragment(R.id.fragment_container, ExpenseFragment.newInstance(expense),
                    ExpenseFragment.TAG).commit();
        } else {
            setUpToolbar(getString(R.string.new_expense));
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
            default:
                // no-op
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
