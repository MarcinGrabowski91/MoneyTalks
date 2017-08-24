package eu.gitcode.android.moneytalks.api;

import java.util.List;

import eu.gitcode.android.moneytalks.models.api.NoteRest;
import eu.gitcode.android.moneytalks.models.request.NoteRequest;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Completable;
import rx.Observable;

public interface NotesApi {
    @GET("notes/notesList")
    Observable<List<NoteRest>> getNotes();

    @POST("notes/edit")
    Completable addOrUpdateNote(@Body NoteRequest noteRequest);
}