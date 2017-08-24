package eu.gitcode.android.moneytalks.models.api;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class NoteRest {

    public static TypeAdapter<NoteRest> typeAdapter(Gson gson) {
        return new AutoValue_NoteRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("value")
    public abstract String content();
}