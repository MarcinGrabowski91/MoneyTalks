package eu.gitcode.android.moneytalks.ui.feature.notes.show;

import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpPresenterRest;
import eu.gitcode.android.moneytalks.ui.common.base.MvpViewRest;

public interface NoteContract {
    interface View extends MvpViewRest {
        void showNoteData(Note note);

        void showRemoveSuccessView();
    }

    interface Presenter extends MvpPresenterRest<View> {
        Note getNote();

        void handleNoteData(Note note);

        void handleRemoveNote();
    }
}