package eu.gitcode.android.moneytalks.api;

import eu.gitcode.android.moneytalks.models.api.TokenRest;
import eu.gitcode.android.moneytalks.models.request.LoginRequest;
import eu.gitcode.android.moneytalks.models.request.RegisterRequest;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Completable;
import rx.Observable;

public interface AuthApi {
    @POST("identity/login")
    Observable<TokenRest> login(@Body LoginRequest loginRequest);

    @POST("identity/createMember")
    Completable register(@Body RegisterRequest registerRequest);
}