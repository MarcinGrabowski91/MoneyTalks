package eu.gitcode.android.moneytalks.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LoginController {
    private final PreferenceController preferenceController;

    @Inject
    public LoginController(PreferenceController preferenceController) {
        this.preferenceController = preferenceController;
    }
}