package eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Transaction;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class AddEditExpenseActivity extends BaseActivity {

    public static final String TRANSACTION = "transaction";

    public static final int ADD_EDIT_EXPENSE_REQUEST_CODE = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Transaction transaction) {
        Intent intent = new Intent(fragment.getContext(), AddEditExpenseActivity.class);
        intent.putExtra(TRANSACTION, transaction);
        fragment.startActivityForResult(intent, ADD_EDIT_EXPENSE_REQUEST_CODE);
    }

    public static void startActivityForResult(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), AddEditExpenseActivity.class);
        fragment.startActivityForResult(intent, ADD_EDIT_EXPENSE_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        if (savedInstanceState == null) {
            AddEditExpenseFragment addEditExpenseFragment;
            if (getIntent().hasExtra(TRANSACTION)) {
                Transaction transaction = getIntent().getParcelableExtra(TRANSACTION);
                addEditExpenseFragment = AddEditExpenseFragment.newInstance(transaction);
                setUpToolbar(transaction.name());
            } else {
                addEditExpenseFragment = new AddEditExpenseFragment();
                setUpToolbar(getString(R.string.new_expense));
            }

            replaceFragment(R.id.fragment_container, addEditExpenseFragment,
                    AddEditExpenseFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.done_menu_btn) {
            AddEditExpenseFragment clientProfileFragment =
                    (AddEditExpenseFragment) getSupportFragmentManager()
                            .findFragmentByTag(AddEditExpenseFragment.TAG);
            if (clientProfileFragment != null) {
                clientProfileFragment.addOrUpdateExpense();
            }
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
