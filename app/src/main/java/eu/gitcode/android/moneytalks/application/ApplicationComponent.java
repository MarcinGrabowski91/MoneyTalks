package eu.gitcode.android.moneytalks.application;


import javax.inject.Singleton;

import dagger.Component;
import eu.gitcode.android.moneytalks.DebugMetricsHelper;
import eu.gitcode.android.moneytalks.controllers.AuthController;
import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.modules.AppModule;
import eu.gitcode.android.moneytalks.ui.feature.budget.categories.CategoriesComponent;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.addedit.AddEditExpenseComponent;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.list.ExpensesComponent;
import eu.gitcode.android.moneytalks.ui.feature.budget.expenses.show.ExpenseComponent;
import eu.gitcode.android.moneytalks.ui.feature.budget.subcategories.SubcategoriesComponent;
import eu.gitcode.android.moneytalks.ui.feature.budget.summary.BudgetSummaryComponent;
import eu.gitcode.android.moneytalks.ui.feature.login.LoginComponent;
import eu.gitcode.android.moneytalks.ui.feature.main.MainComponent;
import eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteComponent;
import eu.gitcode.android.moneytalks.ui.feature.notes.list.NotesComponent;
import eu.gitcode.android.moneytalks.ui.feature.notes.show.NoteComponent;
import eu.gitcode.android.moneytalks.ui.feature.register.RegisterComponent;
import eu.gitcode.android.moneytalks.ui.feature.summary.SummaryComponent;

@Singleton
@Component(
        modules = {AppModule.class}
)

public interface ApplicationComponent {
    DebugMetricsHelper getDebugMetricsHelper();

    PreferenceController getPreferenceController();

    AuthController getAuthController();

    LoginComponent getLoginFragmentComponent();

    RegisterComponent getRegisterComponent();

    MainComponent getMainComponent();

    SummaryComponent getSummaryComponent();

    BudgetSummaryComponent getBudgetSummaryComponent();

    ExpensesComponent getExpensesComponent();

    ExpenseComponent getExpenseComponent();

    AddEditExpenseComponent getAddEditExpenseComponent();

    NotesComponent getNotesComponent();

    AddEditNoteComponent getAddEditNoteComponent();

    NoteComponent getNoteComponent();

    CategoriesComponent getCategoriesComponent();

    SubcategoriesComponent getSubcategoriesComponent();

    void inject(App app);
}
