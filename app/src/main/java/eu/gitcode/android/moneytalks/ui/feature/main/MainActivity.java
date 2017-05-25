package eu.gitcode.android.moneytalks.ui.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpActivity;
import eu.gitcode.android.moneytalks.ui.feature.budget.summary.BudgetSummaryFragment;
import eu.gitcode.android.moneytalks.ui.feature.login.LoginActivity;
import eu.gitcode.android.moneytalks.ui.feature.notes.list.NotesFragment;
import eu.gitcode.android.moneytalks.ui.feature.notifications.NotificationsFragment;
import eu.gitcode.android.moneytalks.ui.feature.summary.SummaryFragment;

public class MainActivity extends BaseMvpActivity<MainActivityContract.View,
        MainActivityContract.Presenter> implements MainActivityContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_drawer)
    DrawerLayout navigationDrawer;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    TextView usernameNavigationHeaderTxt;


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initToolbarAndNavigationDrawer();
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, new SummaryFragment(), SummaryFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().loadUserNavigationHeaderData();
    }

    @NonNull
    @Override
    public MainActivityContract.Presenter createPresenter() {
        MainComponent component = App.getAppComponent(this).getMainComponent();
        component.inject(this);
        return component.getMainActivityPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showUserNavigationDrawerFullName(String fullName) {
        usernameNavigationHeaderTxt.setText(fullName);
    }

    @Override
    public void showLoginView() {
        LoginActivity.startActivity(this);
        finish();
    }

    private void initToolbarAndNavigationDrawer() {
        setUpToolbar();
        setupNavigationDrawer();
        setActionBarToggle();
    }

    private void setUpToolbar() {
        toolbar.setTitle(R.string.summary);
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        View headerView = navigationView.getHeaderView(0);
        usernameNavigationHeaderTxt = ButterKnife.findById(headerView, R.id.username_navigation_header_txt);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            navigationDrawer.closeDrawers();
            switch (menuItem.getItemId()) {
                case R.id.summary_item:
                    toolbar.setTitle(R.string.summary);
                    replaceFragment(R.id.fragment_container, new SummaryFragment(),
                            SummaryFragment.TAG).commit();
                    break;
                case R.id.budget_item:
                    toolbar.setTitle(R.string.budget);
                    replaceFragment(R.id.fragment_container, new BudgetSummaryFragment(),
                            BudgetSummaryFragment.TAG).commit();
                    break;
                case R.id.investment_item:
                    toolbar.setTitle(R.string.investments);
                    break;
                case R.id.notifications_item:
                    toolbar.setTitle(R.string.notifications);
                    replaceFragment(R.id.fragment_container, new NotificationsFragment(),
                            NotificationsFragment.TAG).commit();
                    break;
                case R.id.reminders_item:
                    toolbar.setTitle(R.string.reminders);
                    break;
                case R.id.notes_item:
                    toolbar.setTitle(R.string.notes);
                    replaceFragment(R.id.fragment_container, new NotesFragment(),
                            NotesFragment.TAG).commit();
                    break;
                case R.id.logout_item:
                    logout();
                    break;
                default:
                    throw new IllegalArgumentException("Wrong navigation drawer id");
            }
            return true;
        });
    }

    private void setActionBarToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, navigationDrawer, toolbar, R.string.open, R.string.close) {
                    public void onDrawerClosed(View view) {
                        super.onDrawerClosed(view);
                        invalidateOptionsMenu();
                        syncState();
                    }
                };

        navigationDrawer.addDrawerListener(actionBarDrawerToggle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        actionBarDrawerToggle.syncState();
    }

    private void logout() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.do_you_want_to_logout)
                .setPositiveButton(R.string.yes, (dialog, which) -> getPresenter().logout())
                .setNegativeButton(R.string.no, (dialog, which) -> {
                }).create().show();
    }
}