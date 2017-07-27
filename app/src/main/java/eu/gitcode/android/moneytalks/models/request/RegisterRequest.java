package eu.gitcode.android.moneytalks.models.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class RegisterRequest {

    public static TypeAdapter<RegisterRequest> typeAdapter(Gson gson) {
        return new AutoValue_RegisterRequest.GsonTypeAdapter(gson);
    }

    public static RegisterRequest create(String name, String email, String password) {
        return new AutoValue_RegisterRequest(name, email, password);
    }

    @SerializedName("name")
    public abstract String name();

    @SerializedName("email")
    public abstract String email();

    @SerializedName("password")
    public abstract String password();
}