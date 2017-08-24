package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import javax.inject.Inject;

import eu.gitcode.android.moneytalks.controllers.NotesController;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.MvpBasePresenterRest;
import eu.gitcode.android.moneytalks.utils.RxTransformers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

@FragmentScope
public final class NotesFragmentPresenter extends MvpBasePresenterRest<NotesAdapterContract.View>
        implements NotesAdapterContract.Presenter {
    private final NotesController notesController;

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public NotesFragmentPresenter(NotesController notesController) {
        this.notesController = notesController;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        subscriptions.unsubscribe();
    }

    @Override
    public void handleNotesData() {
        subscriptions.add(notesController.getNotes()
                .map(Note::fromRest)
                .compose(RxTransformers.applySchedulers())
                .subscribe(notes -> getView().showNotesData(notes),
                        throwable -> Timber.d("Error during notes loading: %s",
                                throwable.getLocalizedMessage())));
    }

    @Override
    public void handleRemoveNote(Note note) {
        getView().showRemoveSuccessView();
    }
}
