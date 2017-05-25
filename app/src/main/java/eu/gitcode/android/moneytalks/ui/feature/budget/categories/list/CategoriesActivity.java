package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class CategoriesActivity extends BaseActivity {

    public static final int CATEGORY_REQUEST = 1;
    public static final String CATEGORY = "CATEGORY";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Category category) {
        Intent intent = new Intent(fragment.getContext(), CategoriesActivity.class);
        intent.putExtra(CATEGORY, category);
        fragment.startActivityForResult(intent, CATEGORY_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, new CategoriesFragment(), CategoriesFragment.TAG)
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
        toolbar.setTitle(getString(R.string.category));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
