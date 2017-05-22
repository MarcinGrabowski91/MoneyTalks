package eu.gitcode.android.moneytalks.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthController {
    private final PreferenceController preferenceController;

    @Inject
    public AuthController(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }
}