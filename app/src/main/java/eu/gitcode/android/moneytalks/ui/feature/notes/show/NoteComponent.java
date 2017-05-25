package eu.gitcode.android.moneytalks.ui.feature.notes.show;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface NoteComponent {

    void inject(NoteFragment noteFragment);

    NoteFragmentPresenter getNotePresenter();
}