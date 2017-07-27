package eu.gitcode.android.moneytalks.models.request;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class LoginRequest {

    public static TypeAdapter<LoginRequest> typeAdapter(Gson gson) {
        return new AutoValue_LoginRequest.GsonTypeAdapter(gson);
    }

    public static LoginRequest create(String email, String password) {
        return new AutoValue_LoginRequest(email, password);
    }

    @SerializedName("email")
    public abstract String email();

    @SerializedName("password")
    public abstract String password();
}