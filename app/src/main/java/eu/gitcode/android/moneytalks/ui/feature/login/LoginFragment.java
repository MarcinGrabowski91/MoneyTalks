package eu.gitcode.android.moneytalks.ui.feature.login;

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
import eu.gitcode.android.moneytalks.ui.feature.register.RegisterActivity;
import eu.gitcode.android.moneytalks.utils.UIUtils;
import onactivityresult.ActivityResult;
import onactivityresult.OnActivityResult;

import static android.app.Activity.RESULT_OK;

public class LoginFragment extends BaseMvpFragment<LoginContract.View, LoginContract.Presenter>
        implements LoginContract.View {
    public static final String TAG = LoginFragment.class.getSimpleName();

    private static final int REGISTER_REQUEST_CODE = 1;

    @BindView(R.id.log_in_btn)
    Button loginBtn;

    @BindView(R.id.email_edit)
    TextInputEditText emailEdit;

    @BindView(R.id.password_edit)
    TextInputEditText passwordEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    @NonNull
    public LoginFragmentPresenter createPresenter() {
        LoginComponent component = App.getAppComponent(getContext()).getLoginFragmentComponent();
        component.inject(this);
        return component.getLoginFragmentPresenter();
    }

    @OnClick(R.id.new_account_txt)
    void onNewAccountClick() {
        RegisterActivity.startActivity(this, REGISTER_REQUEST_CODE);
    }

    @OnClick(R.id.log_in_btn)
    void onLogInBtnClicked() {
        loginBtn.setEnabled(false);
        UIUtils.hideFieldsError(emailEdit, passwordEdit);
        getPresenter().loginWithCredentials(emailEdit.getText().toString(), passwordEdit.getText().toString());
    }

    @Override
    public void showWrongCredentialsError() {
        showSnackbar(R.string.wrong_email_or_password);
        loginBtn.setEnabled(true);
    }

    @Override
    public void showLoginSuccessfulView() {
        // TODO add main activity
    }

    @Override
    public void showDataFillError() {
        UIUtils.showFillError(getContext(), emailEdit, passwordEdit);
        loginBtn.setEnabled(true);
    }

    @Override
    public void emailNotValidError() {
        emailEdit.setError(getString(R.string.email_not_valid));
        emailEdit.requestFocus();
        loginBtn.setEnabled(true);
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
        loginBtn.setEnabled(true);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = REGISTER_REQUEST_CODE)
    void onActivityResultRegisterSuccess(final int resultCode) {
        if (RESULT_OK == resultCode) {
            getActivity().finish();
        }
    }
}