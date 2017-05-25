package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface AddEditNoteComponent {

    void inject(AddEditNoteFragment addEditNoteFragment);

    AddEditNoteFragmentPresenter getAddEditNotePresenter();
}