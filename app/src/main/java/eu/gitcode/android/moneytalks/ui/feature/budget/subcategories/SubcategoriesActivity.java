package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class SubcategoriesActivity extends BaseActivity {

    public static final int SUBCATEGORY_REQUEST = 1;
    public static final String CATEGORY_ID = "category_id";
    public static final String IS_EXPENSES_PATH = "is_expenses_path";
    public static final String SUBCATEGORIES = "subcategories";
    private static final String TITLE = "title";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Long categoryId) {
        Intent intent = new Intent(fragment.getContext(), SubcategoriesActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        fragment.startActivityForResult(intent, SUBCATEGORY_REQUEST);
    }

    public static void startActivity(Context context, String title, Long categoryId,
                                     List<Subcategory> subcategoriesList, boolean isExpensesPath) {
        Intent intent = new Intent(context, SubcategoriesActivity.class);
        intent.putExtra(TITLE, title);
        intent.putParcelableArrayListExtra(SUBCATEGORIES, new ArrayList<>(subcategoriesList));
        intent.putExtra(CATEGORY_ID, categoryId);
        intent.putExtra(IS_EXPENSES_PATH, isExpensesPath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);

        if (savedInstanceState == null) {
            Long categoryId = getIntent().getLongExtra(CATEGORY_ID, 0L);
            boolean isExpensesPath = getIntent().getBooleanExtra(IS_EXPENSES_PATH, false);
            List<Subcategory> subcategoriesList = getIntent().getParcelableArrayListExtra(SUBCATEGORIES);
            if (subcategoriesList != null) {
                replaceFragment(R.id.fragment_container,
                        SubcategoriesFragment.newInstance(categoryId, isExpensesPath, subcategoriesList),
                        SubcategoriesFragment.TAG).commit();
            } else {
                replaceFragment(R.id.fragment_container,
                        SubcategoriesFragment.newInstance(categoryId),
                        SubcategoriesFragment.TAG).commit();
            }
        }
        if (getIntent().getStringExtra(TITLE) != null) {
            setUpToolbar(getIntent().getStringExtra(TITLE));
        } else {
            setUpToolbar(getString(R.string.subcategory));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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
