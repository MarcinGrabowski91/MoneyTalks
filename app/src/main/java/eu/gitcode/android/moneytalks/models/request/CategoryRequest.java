package eu.gitcode.android.moneytalks.models.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class CategoryRequest {

    public static TypeAdapter<CategoryRequest> typeAdapter(Gson gson) {
        return new AutoValue_CategoryRequest.GsonTypeAdapter(gson);
    }

    public static CategoryRequest create(String name) {
        return new AutoValue_CategoryRequest(name);
    }

    @SerializedName("name")
    public abstract String name();
}