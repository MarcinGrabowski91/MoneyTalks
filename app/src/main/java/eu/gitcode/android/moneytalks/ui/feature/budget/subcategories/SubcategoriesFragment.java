package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list.ExpensesActivity;

import static android.app.Activity.RESULT_OK;
import static eu.gitcode.android.moneytalks.ui.feature.budget.categories.CategoriesActivity.SUBCATEGORY;
import static eu.gitcode.android.moneytalks.ui.feature.budget.subcategories.SubcategoriesActivity.CATEGORY_ID;
import static eu.gitcode.android.moneytalks.ui.feature.budget.subcategories.SubcategoriesActivity.IS_EXPENSES_PATH;
import static eu.gitcode.android.moneytalks.ui.feature.budget.subcategories.SubcategoriesActivity.SUBCATEGORIES;

public class SubcategoriesFragment extends BaseMvpFragment<SubcategoriesContract.View,
        SubcategoriesContract.Presenter> implements SubcategoriesContract.View {
    public static final String TAG = SubcategoriesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.floating_btn)
    FloatingActionButton floatingActionButton;

    SubcategoriesAdapter adapter;

    public static SubcategoriesFragment newInstance(Long id, boolean isExpensesPath,
                                                    List<Subcategory> subcategoriesList) {
        SubcategoriesFragment subcategoriesFragment = new SubcategoriesFragment();
        Bundle args = new Bundle();
        args.putLong(CATEGORY_ID, id);
        args.putBoolean(IS_EXPENSES_PATH, isExpensesPath);
        args.putParcelableArrayList(SUBCATEGORIES, new ArrayList<>(subcategoriesList));
        subcategoriesFragment.setArguments(args);

        return subcategoriesFragment;
    }

    public static SubcategoriesFragment newInstance(Long id) {
        SubcategoriesFragment subcategoriesFragment = new SubcategoriesFragment();
        Bundle args = new Bundle();
        args.putLong(CATEGORY_ID, id);
        subcategoriesFragment.setArguments(args);

        return subcategoriesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subcategories_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            getPresenter().saveCategoryId(getArguments().getLong(CATEGORY_ID));
        }
        setupRecyclerView();
        List<Subcategory> subcategoriesList = getArguments().getParcelableArrayList(SUBCATEGORIES);
        if (subcategoriesList != null) {
            showSubcategoriesList(subcategoriesList);
            floatingActionButton.setVisibility(View.GONE);
        } else {
            getPresenter().loadSubcategories();
        }
    }

    @Override
    @NonNull
    public SubcategoriesFragmentPresenter createPresenter() {
        SubcategoriesComponent component = App.getAppComponent(getContext())
                .getSubcategoriesComponent();
        component.inject(this);
        return component.getSubcategoriesFragmentPresenter();
    }

    @OnClick(R.id.floating_btn)
    void onFloatingBtnClick() {
        final EditText titleEdit = new EditText(getContext());
        new AlertDialog.Builder(getContext())
                .setView(titleEdit)
                .setTitle(R.string.new_subcategory_title)
                .setPositiveButton(R.string.ok, (dialog, whichButton) ->
                        getPresenter().handleAddSubcategory(titleEdit.getText().toString()))
                .setNegativeButton(R.string.cancel, (dialog, whichButton) -> { // no-op
                }).show();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showSubcategoriesList(List<Subcategory> subcategoriesList) {
        adapter.setSubcategories(subcategoriesList);
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().loadSubcategories();
    }

    private void setupRecyclerView() {
        SubcategoriesViewHolder.SubcategoryViewHolderListener listener
                = new SubcategoriesViewHolder.SubcategoryViewHolderListener() {
            @Override
            public void onSubcategoryLongClicked(Subcategory subcategory) {
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        showUpdateSubcategoryDialog(subcategory);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveSubcategoryDialog(subcategory);
                                    }
                                }).show();
            }

            @Override
            public void onSubcategoryClicked(Subcategory subcategory) {
                if (getArguments().getBoolean(IS_EXPENSES_PATH, false)) {
                    ExpensesActivity.startActivity(getContext(), subcategory.name(), subcategory.transactionsList());
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(SUBCATEGORY, subcategory);
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            }
        };

        adapter = new SubcategoriesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showUpdateSubcategoryDialog(Subcategory subcategory) {
        final EditText titleEdit = new EditText(getContext());
        titleEdit.setText(subcategory.name());
        titleEdit.setHint(R.string.title);
        new AlertDialog.Builder(getContext())
                .setView(titleEdit)
                .setPositiveButton(R.string.ok,
                        (titleDialog, whichButton) ->
                                getPresenter().handleUpdateSubcategory(subcategory))
                .setNegativeButton(R.string.cancel,
                        (titleDialog, whichButton) -> { // no-op
                        }).create().show();
    }

    private void showRemoveSubcategoryDialog(Subcategory subcategory) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_category)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveSubcategory(subcategory))
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}