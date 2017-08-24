package eu.gitcode.android.moneytalks.models.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class SubcategoryRequest {

    public static TypeAdapter<SubcategoryRequest> typeAdapter(Gson gson) {
        return new AutoValue_SubcategoryRequest.GsonTypeAdapter(gson);
    }

    public static SubcategoryRequest create(long categoryId, String name) {
        return new AutoValue_SubcategoryRequest(categoryId, name);
    }

    @SerializedName("categoryId")
    public abstract long categoryId();

    @SerializedName("name")
    public abstract String name();
}