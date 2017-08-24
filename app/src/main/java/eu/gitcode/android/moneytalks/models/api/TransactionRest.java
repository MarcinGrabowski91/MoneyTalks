package eu.gitcode.android.moneytalks.models.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

@AutoValue
public abstract class TransactionRest {

    public static TypeAdapter<TransactionRest> typeAdapter(Gson gson) {
        return new AutoValue_TransactionRest.GsonTypeAdapter(gson);
    }

    @SerializedName("id")
    public abstract Long id();

    @SerializedName("subcategoryMonthId")
    public abstract Long subcategoryMonthId(); //TODO change to categoryId

    @SerializedName("name")
    public abstract String name();

    @SerializedName("date")
    public abstract DateTime date();

    @SerializedName("value")
    public abstract Float value();
}