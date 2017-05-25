package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.enumeration.ItemActionChooserEnum;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;

import static android.app.Activity.RESULT_OK;

public class CategoriesFragment extends BaseMvpFragment<CategoriesContract.View,
        CategoriesContract.Presenter> implements CategoriesContract.View {
    public static final String TAG = CategoriesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    CategoriesAdapter adapter;

    public static CategoriesFragment newInstance(Category category) {
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putParcelable(CategoriesActivity.CATEGORY, category);
        categoriesFragment.setArguments(args);

        return categoriesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.categories_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().handleCategoriesData();
    }

    @Override
    @NonNull
    public CategoriesFragmentPresenter createPresenter() {
        CategoriesComponent component = App.getAppComponent(getContext()).getCategoriesComponent();
        component.inject(this);
        return component.getCategoriesFragmentPresenter();
    }

    @OnClick(R.id.floating_btn)
    void onFloatingBtnClick() {
        final EditText titleEdit = new EditText(getContext());
        new AlertDialog.Builder(getContext())
                .setView(titleEdit)
                .setTitle(R.string.title)
                .setPositiveButton(R.string.ok, (dialog, whichButton) ->
                        getPresenter().handleAddCategory(titleEdit.getText().toString()))
                .setNegativeButton(R.string.cancel, (dialog, whichButton) -> {
                })
                .show();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showCategoriesData() {
        //TODO load categories data from the server
        List<Category> categoriesList = new ArrayList<>();
        categoriesList.add(Category.builder().name("Clothes").build());
        categoriesList.add(Category.builder().name("Electronics").build());
        categoriesList.add(Category.builder().name("Car").build());
        categoriesList.add(Category.builder().name("House").build());
        adapter.setCategories(categoriesList);
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().handleCategoriesData();
    }

    private void setupRecyclerView() {
        CategoriesViewHolder.ExpenseViewHolderListener listener
                = new CategoriesViewHolder.ExpenseViewHolderListener() {
            @Override
            public void onCategoryLongClicked(Category category) {
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        showUpdateCategoryDialog(category);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveCategoryDialog(category);
                                    }
                                }).show();
            }

            @Override
            public void onCategoryClicked(Category category) {
                Intent intent = new Intent();
                intent.putExtra(CategoriesActivity.CATEGORY, category);
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }
        };

        adapter = new CategoriesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showUpdateCategoryDialog(Category category) {
        final EditText titleEdit = new EditText(getContext());
        titleEdit.setText(category.name());
        titleEdit.setHint(R.string.title);
        new AlertDialog.Builder(getContext())
                .setView(titleEdit)
                .setPositiveButton(R.string.ok,
                        (titleDialog, whichButton) ->
                                getPresenter().handleUpdateCategory(category))
                .setNegativeButton(R.string.cancel,
                        (titleDialog, whichButton) -> {
                        })
                .show();
    }

    private void showRemoveCategoryDialog(Category category) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_category)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveCategory(category))
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}