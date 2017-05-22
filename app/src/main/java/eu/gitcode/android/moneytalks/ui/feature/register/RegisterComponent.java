package eu.gitcode.android.moneytalks.ui.feature.register;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface RegisterComponent {

    void inject(RegisterFragment registerFragment);

    RegisterFragmentPresenter getRegisterFragmentPresenter();
}