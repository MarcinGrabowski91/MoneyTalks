package eu.gitcode.android.moneytalks.ui.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P> {
    private Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void showSnackbar(@StringRes int stringRes) {
        if (getView() != null) {
            Snackbar.make(getView(), stringRes, Snackbar.LENGTH_LONG).show();
        }
    }

    protected void showSnackbar(String text) {
        if (getView() != null) {
            Snackbar.make(getView(), text, Snackbar.LENGTH_LONG).show();
        }
    }

    protected void showToast(@StringRes int stringRes) {
        Toast.makeText(getContext(), stringRes, Toast.LENGTH_SHORT).show();
    }
}
