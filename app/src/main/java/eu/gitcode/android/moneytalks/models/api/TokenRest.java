package eu.gitcode.android.moneytalks.models.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class TokenRest {

    public static TypeAdapter<TokenRest> typeAdapter(Gson gson) {
        return new AutoValue_TokenRest.GsonTypeAdapter(gson);
    }

    public static TokenRest create(String token) {
        return new AutoValue_TokenRest(token);
    }

    @SerializedName("token")
    public abstract String token();
}