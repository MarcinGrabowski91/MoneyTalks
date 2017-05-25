package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface SubcategoriesContract {
    interface View extends MvpViewRest {
        void showSubcategoriesData();

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleCategoriesData();

        void handleRemoveSubcategory(Subcategory subcategory);

        void handleAddSubcategory(String title);

        void handleUpdateSubcategory(Subcategory subcategory);
    }
}