package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface CategoriesComponent {

    void inject(CategoriesFragment categoriesFragment);

    CategoriesFragmentPresenter getCategoriesFragmentPresenter();
}