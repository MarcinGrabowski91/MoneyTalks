package eu.gitcode.android.moneytalks.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.api.NotesApi;
import eu.gitcode.android.moneytalks.models.api.NoteRest;
import eu.gitcode.android.moneytalks.models.request.NoteRequest;
import rx.Completable;
import rx.Observable;

@Singleton
public class NotesController {
    private final NotesApi notesApi;

    @Inject
    public NotesController(NotesApi notesApi) {
        this.notesApi = notesApi;
    }

    public Observable<List<NoteRest>> getNotes() {
        return notesApi.getNotes();
    }

    public Completable addOrUpdateNote(NoteRequest noteRequest) {
        return notesApi.addOrUpdateNote(noteRequest);
    }
}