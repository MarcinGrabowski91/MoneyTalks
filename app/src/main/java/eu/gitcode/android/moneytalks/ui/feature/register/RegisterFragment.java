package eu.gitcode.android.moneytalks.ui.feature.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.main.MainActivity;
import eu.gitcode.android.moneytalks.utils.UIUtils;
import onactivityresult.ActivityResult;

public class RegisterFragment extends BaseMvpFragment<RegisterContract.View, RegisterContract.Presenter>
        implements RegisterContract.View {
    public static final String TAG = RegisterFragment.class.getSimpleName();

    @BindView(R.id.register_btn)
    Button registerBtn;

    @BindView(R.id.first_name_edit)
    TextInputEditText firstName;

    @BindView(R.id.last_name_edit)
    TextInputEditText lastName;

    @BindView(R.id.email_edit)
    TextInputEditText emailEdit;

    @BindView(R.id.password_edit)
    TextInputEditText passwordEdit;

    @BindView(R.id.re_password_edit)
    TextInputEditText rePasswordEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @NonNull
    @Override
    public RegisterFragmentPresenter createPresenter() {
        RegisterComponent component = App.getAppComponent(getContext()).getRegisterComponent();
        component.inject(this);
        return component.getRegisterFragmentPresenter();
    }

    @OnClick(R.id.register_btn)
    void onRegisterBtnClicked() {
        registerBtn.setEnabled(false);
        UIUtils.hideFieldsError(firstName, lastName, emailEdit, passwordEdit, rePasswordEdit);
        getPresenter().registerAccount(firstName.getText().toString(),
                lastName.getText().toString(), emailEdit.getText().toString(),
                passwordEdit.getText().toString(), rePasswordEdit.getText().toString());
    }

    @Override
    public void showEmailTakenError() {
        showSnackbar(R.string.wrong_email_or_password);
        registerBtn.setEnabled(true);
    }

    @Override
    public void showDataFillError() {
        UIUtils.showFillError(getContext(), firstName, lastName, emailEdit, passwordEdit,
                rePasswordEdit);
        registerBtn.setEnabled(true);
    }

    @Override
    public void showRegisterSuccessView() {
        MainActivity.startActivity(getContext());
        getActivity().finish();
    }

    @Override
    public void showPasswordsAreDifferentError() {
        passwordEdit.setError(getString(R.string.passwords_not_the_same));
        rePasswordEdit.setError(getString(R.string.passwords_not_the_same));
        passwordEdit.requestFocus();
        registerBtn.setEnabled(true);
    }

    @Override
    public void showPasswordTooShortError() {
        passwordEdit.setError(getString(R.string.password_too_short));
        passwordEdit.requestFocus();
        registerBtn.setEnabled(true);
    }

    @Override
    public void emailNotValidError() {
        emailEdit.setError(getString(R.string.email_not_valid));
        emailEdit.requestFocus();
        registerBtn.setEnabled(true);
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
        registerBtn.setEnabled(true);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }
}