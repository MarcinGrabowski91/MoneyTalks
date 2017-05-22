package eu.gitcode.android.moneytalks.ui.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, new LoginFragment(), LoginFragment.TAG).commit();
        }
    }
}
