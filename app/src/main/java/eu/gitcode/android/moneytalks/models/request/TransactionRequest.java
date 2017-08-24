package eu.gitcode.android.moneytalks.models.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

@AutoValue
public abstract class TransactionRequest {

    public static Builder builder() {
        return new AutoValue_TransactionRequest.Builder();
    }

    public static TypeAdapter<TransactionRequest> typeAdapter(Gson gson) {
        return new AutoValue_TransactionRequest.GsonTypeAdapter(gson);
    }

    @SerializedName("name")
    public abstract String name();

    @SerializedName("subcategoryMonthId")
    public abstract Long subcategoryMonthId();

    @SerializedName("date")
    public abstract DateTime dateTime();

    @SerializedName("value")
    public abstract Float value();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder subcategoryMonthId(Long subcategoryMonthId);

        public abstract Builder dateTime(DateTime dateTime);

        public abstract Builder value(Float value);

        public abstract TransactionRequest build();
    }
}