package eu.gitcode.android.moneytalks.models.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class CategoryRest {

    public static TypeAdapter<CategoryRest> typeAdapter(Gson gson) {
        return new AutoValue_CategoryRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("subcategoryList")
    public abstract List<SubcategoryRest> subcategoriesRest();
}