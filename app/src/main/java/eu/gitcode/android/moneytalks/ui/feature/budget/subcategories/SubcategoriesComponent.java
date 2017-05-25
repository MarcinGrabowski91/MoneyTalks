package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface SubcategoriesComponent {

    void inject(SubcategoriesFragment subcategoriesFragment);

    SubcategoriesFragmentPresenter getSubcategoriesFragmentPresenter();
}