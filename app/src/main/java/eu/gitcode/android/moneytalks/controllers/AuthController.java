package eu.gitcode.android.moneytalks.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.api.AuthApi;
import eu.gitcode.android.moneytalks.models.api.TokenRest;
import eu.gitcode.android.moneytalks.models.request.LoginRequest;
import eu.gitcode.android.moneytalks.models.request.RegisterRequest;
import rx.Completable;
import rx.Observable;

@Singleton
public class AuthController {
    private final PreferenceController preferenceController;
    private final AuthApi authApi;

    @Inject
    public AuthController(PreferenceController preferenceController, AuthApi authApi) {
        this.preferenceController = preferenceController;
        this.authApi = authApi;
    }

    public Observable<TokenRest> login(String email, String password) {
        LoginRequest loginRequest = LoginRequest.create(email, password);
        return authApi.login(loginRequest).doOnNext(preferenceController::saveToken);
    }

    public Completable register(String name, String email, String password) {
        RegisterRequest registerRequest = RegisterRequest.create(name, email, password);
        return authApi.register(registerRequest);
    }
}