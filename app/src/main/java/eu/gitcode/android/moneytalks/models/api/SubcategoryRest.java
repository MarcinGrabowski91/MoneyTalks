package eu.gitcode.android.moneytalks.models.api;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class SubcategoryRest {

    public static TypeAdapter<SubcategoryRest> typeAdapter(Gson gson) {
        return new AutoValue_SubcategoryRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @Nullable
    @SerializedName("categoryId")
    public abstract Long categoryId();

    @Nullable
    @SerializedName("categoryMonthId")
    public abstract Long categoryMonthId(); //TODO change to categoryId

    @SerializedName("name")
    public abstract String name();

    @Nullable
    @SerializedName("budgeted")
    public abstract Float budgeted();

    @Nullable
    @SerializedName("transactionList")
    public abstract List<TransactionRest> transactionsRestList();
}