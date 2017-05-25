package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;
import eu.gitcode.android.moneytalks.ui.feature.budget.categories.CategoriesActivity;

public class SubcategoriesActivity extends BaseActivity {

    public static final int SUBCATEGORY_REQUEST = 1;
    public static final String SUBCATEGORY = "CATEGORY";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Category category) {
        Intent intent = new Intent(fragment.getContext(), SubcategoriesActivity.class);
        intent.putExtra(SUBCATEGORY, category);
        fragment.startActivityForResult(intent, SUBCATEGORY_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null) {
            if (getIntent().hasExtra(CategoriesActivity.CATEGORY)) {
                Category category = getIntent().getParcelableExtra(CategoriesActivity.CATEGORY);
                replaceFragment(R.id.fragment_container,
                        SubcategoriesFragment.newInstance(category), SubcategoriesFragment.TAG)
                        .commit();
            } else {
                replaceFragment(R.id.fragment_container, new SubcategoriesFragment(),
                        SubcategoriesFragment.TAG).commit();
            }
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
        toolbar.setTitle(getString(R.string.subcategory));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
