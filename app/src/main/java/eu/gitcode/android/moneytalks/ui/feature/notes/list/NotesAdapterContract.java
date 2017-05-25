package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface NotesAdapterContract {
    interface View extends MvpViewRest {
        void showNotesData();

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleNotesData();

        void handleRemoveNote(Note note);
    }
}