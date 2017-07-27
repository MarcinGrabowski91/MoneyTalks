package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class SubcategoriesActivity extends BaseActivity {

    public static final int SUBCATEGORY_REQUEST = 1;
    public static final String CATEGORY_ID = "category_id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Long categoryId) {
        Intent intent = new Intent(fragment.getContext(), SubcategoriesActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        fragment.startActivityForResult(intent, SUBCATEGORY_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        setUpToolbar();
        if (savedInstanceState == null) {
            if (getIntent().hasExtra(CATEGORY_ID)) {
                Long categoryId = getIntent().getLongExtra(CATEGORY_ID, 0L);
                replaceFragment(R.id.fragment_container,
                        SubcategoriesFragment.newInstance(categoryId), SubcategoriesFragment.TAG)
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
