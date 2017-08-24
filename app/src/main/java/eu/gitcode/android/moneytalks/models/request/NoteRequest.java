package eu.gitcode.android.moneytalks.models.request;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class NoteRequest {

    public static TypeAdapter<NoteRequest> typeAdapter(Gson gson) {
        return new AutoValue_NoteRequest.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_NoteRequest.Builder();
    }

    @Nullable
    @SerializedName("id")
    public abstract Long id();

    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("value")
    public abstract String content();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder content(String content);

        public abstract NoteRequest build();
    }
}