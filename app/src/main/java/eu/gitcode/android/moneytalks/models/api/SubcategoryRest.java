package eu.gitcode.android.moneytalks.models.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class SubcategoryRest {

    public static TypeAdapter<SubcategoryRest> typeAdapter(Gson gson) {
        return new AutoValue_SubcategoryRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("categoryId")
    public abstract Long categoryId();

    @SerializedName("name")
    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder categoryId(Long categoryId);

        public abstract Builder name(String name);

        public abstract SubcategoryRest build();
    }
}