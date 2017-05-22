package eu.gitcode.android.moneytalks.controllers;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceController {

    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferenceController(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void clearSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }
}