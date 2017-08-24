package eu.gitcode.android.moneytalks.models.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

@AutoValue
public abstract class BudgetRest {

    public static TypeAdapter<BudgetRest> typeAdapter(Gson gson) {
        return new AutoValue_BudgetRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("date")
    public abstract DateTime date();

    @SerializedName("userId")
    public abstract Long userId();

    @SerializedName("categoryMonthList")
    public abstract List<CategoryRest> categoriesRestList();
}