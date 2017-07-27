package eu.gitcode.android.moneytalks.ui.feature.budget.categories;

import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

interface CategoriesContract {
    interface View extends MvpViewRest {
        void showCategoriesData(List<Category> categoriesList);

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void loadCategoriesData();

        void handleRemoveCategory(Category category);

        void handleAddCategory(String title);

        void handleUpdateCategory(Category category);
    }
}