package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class ExpensesActivity extends BaseActivity {

    private static final String TITLE = "TITLE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivity(Context context, String title) {
        Intent intent = new Intent(context, ExpensesActivity.class);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, new ExpensesFragment(), ExpensesFragment.TAG)
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
