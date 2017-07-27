package eu.gitcode.android.moneytalks.controllers;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.models.api.TokenRest;

@Singleton
public class PreferenceController {

    private static final String TOKEN_KEY = "token_key";

    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferenceController(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveToken(TokenRest tokenRest) {
        sharedPreferences.edit().putString(TOKEN_KEY, tokenRest.token()).apply();
    }

    public TokenRest getToken() {
        return TokenRest.create(sharedPreferences.getString(TOKEN_KEY, ""));
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }
}