package eu.gitcode.android.moneytalks.ui.feature.budget.categories;

import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface CategoriesContract {
    interface View extends MvpViewRest {
        void showCategoriesData();

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleCategoriesData();

        void handleRemoveCategory(Category category);

        void handleAddCategory(String title);

        void handleUpdateCategory(Category category);
    }
}