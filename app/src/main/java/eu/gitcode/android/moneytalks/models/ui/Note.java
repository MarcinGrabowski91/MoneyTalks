package eu.gitcode.android.moneytalks.models.ui;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.gitcode.android.moneytalks.models.api.NoteRest;

@AutoValue
public abstract class Note implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Note.Builder();
    }

    public static Note fromRest(NoteRest noteRest) {
        return Note.builder().id(noteRest.id()).name(noteRest.name()).content(noteRest.content())
                .build();
    }

    public static List<Note> fromRest(List<NoteRest> noteRestsList) {
        if (noteRestsList != null) {
            List<Note> notesList = new ArrayList<>(noteRestsList.size());
            for (NoteRest noteRest : noteRestsList) {
                notesList.add(Note.fromRest(noteRest));
            }
            return notesList;
        }
        return Collections.emptyList();
    }

    public abstract Long id();

    public abstract String name();

    public abstract String content();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder content(String content);

        public abstract Note build();
    }
}
