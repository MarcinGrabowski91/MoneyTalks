package eu.gitcode.android.moneytalks.ui.feature.login;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface LoginComponent {

    void inject(LoginFragment loginFragment);

    LoginFragmentPresenter getLoginFragmentPresenter();
}