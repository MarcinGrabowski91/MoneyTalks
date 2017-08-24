package eu.gitcode.android.moneytalks.models.api;

import android.support.annotation.Nullable;

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

    @Nullable
    @SerializedName("budgetId")
    public abstract Long budgetId();

    @Nullable
    @SerializedName("subcategoryList")
    public abstract List<SubcategoryRest> subcategoriesRest();

    @Nullable
    @SerializedName("subcategoryRestList")
    public abstract List<SubcategoryRest> subcategoriesRestMonth();
}