package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

interface SubcategoriesContract {
    interface View extends MvpViewRest {
        void showSubcategoriesList(List<Subcategory> subcategoriesList);

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void saveCategoryId(Long categoryId);

        void loadSubcategories();

        void handleRemoveSubcategory(Subcategory subcategory);

        void handleAddSubcategory(String title);

        void handleUpdateSubcategory(Subcategory subcategory);
    }
}