package eu.gitcode.android.moneytalks.ui.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
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