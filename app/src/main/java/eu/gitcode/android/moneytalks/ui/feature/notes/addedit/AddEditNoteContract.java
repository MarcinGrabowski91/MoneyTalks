package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface AddEditNoteContract {
    interface View extends MvpViewRest {
        void showNoteData(Note note);

        void showAddOrUpdateSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        void handleNoteData(Note note);

        void addOrUpdateNote(String name, String content);
    }
}