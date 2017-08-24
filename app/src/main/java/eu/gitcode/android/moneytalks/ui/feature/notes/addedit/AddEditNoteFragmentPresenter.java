package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.api.NotesApi;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.request.NoteRequest;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class AddEditNoteFragmentPresenter extends MvpBasePresenterRest<AddEditNoteContract.View>
        implements AddEditNoteContract.Presenter {

    private final NotesApi notesApi;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private Note note;

    @Inject
    public AddEditNoteFragmentPresenter(NotesApi notesApi) {
        this.notesApi = notesApi;
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
    public void addOrUpdateNote(String name, String content) {
        NoteRequest.Builder noteRequest = NoteRequest.builder().name(name)
                .content(content);
        if (note != null) {
            noteRequest.id(note.id());
        }
        subscriptions.add(notesApi.addOrUpdateNote(noteRequest.build())
                .compose(RxTransformers.applyCompletableSchedulers())
                .subscribe(() -> getView().showAddOrUpdateSuccessView(),
                        throwable -> Timber.d("Error during adding or updating note: %s",
                                throwable.getLocalizedMessage())));
    }
}
