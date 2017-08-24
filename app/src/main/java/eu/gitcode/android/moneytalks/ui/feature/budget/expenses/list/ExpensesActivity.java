package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class ExpensesActivity extends BaseActivity {

    public static final String TRANSACTIONS_LIST = "transactions_list";
    private static final String TITLE = "title";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivity(Context context, String title, List<Transaction> transactionList) {
        Intent intent = new Intent(context, ExpensesActivity.class);
        intent.putExtra(TITLE, title);
        intent.putParcelableArrayListExtra(TRANSACTIONS_LIST, new ArrayList<>(transactionList));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null) {
            List<Transaction> transactionsList = getIntent().getParcelableArrayListExtra(TRANSACTIONS_LIST);
            replaceFragment(R.id.fragment_container, ExpensesFragment.newInstance(transactionsList), ExpensesFragment.TAG)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        if (getIntent().hasExtra(TITLE)) {
            toolbar.setTitle(getIntent().getStringExtra(TITLE));
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
