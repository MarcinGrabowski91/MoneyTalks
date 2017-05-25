package eu.gitcode.android.moneytalks.ui.feature.notes.show;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class NoteFragmentPresenter extends MvpBasePresenterRest<NoteContract.View>
        implements NoteContract.Presenter {

    private final PreferenceController preferenceController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private Note note;

    @Inject
    public NoteFragmentPresenter(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleNoteData(Note note) {
        this.note = note;
        getView().showNoteData(note);
    }

    @Override
    public void handleRemoveNote() {
        getView().showRemoveSuccessView();
    }

    @Override
    public Note getNote() {
        return note;
    }
}
