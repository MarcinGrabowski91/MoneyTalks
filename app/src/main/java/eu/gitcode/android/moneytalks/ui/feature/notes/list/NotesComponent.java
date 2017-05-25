package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface NotesComponent {

    void inject(NotesFragment notesFragment);

    NotesFragmentPresenter getNotesPresenter();
}