package eu.gitcode.android.moneytalks.ui.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

public abstract class MvpActivityRest<V extends MvpViewRest, P extends MvpPresenterRest<V>> extends MvpActivity<V, P> {
    @BindView(android.R.id.content)
    View contentView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected FragmentTransaction replaceFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment, tag);
        return ft;
    }

    protected void showSnackbar(@StringRes int stringRes) {
        Snackbar.make(contentView, stringRes, Snackbar.LENGTH_LONG).show();
    }

    protected void showSnackbar(String text) {
        Snackbar.make(contentView, text, Snackbar.LENGTH_LONG).show();
    }

    protected void showToast(@StringRes int stringRes) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show();
    }
}
