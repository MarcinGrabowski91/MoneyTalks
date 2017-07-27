package eu.gitcode.android.moneytalks.api;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import eu.gitcode.android.moneytalks.controllers.PreferenceController;
import eu.gitcode.android.moneytalks.models.api.TokenRest;
import eu.gitcode.android.moneytalks.ui.feature.login.LoginActivity;
import eu.gitcode.android.moneytalks.utils.NetworkUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

@Singleton
final class SessionInterceptor implements Interceptor {

    private final PreferenceController preferenceController;
    private final Context context;


    @Inject
    public SessionInterceptor(PreferenceController preferenceController, Context context) {
        this.preferenceController = preferenceController;
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        TokenRest tokenRest = preferenceController.getToken();
        if (tokenRest == null) {
            return handleRequestWithoutToken(chain);
        } else {
            return handleRequestWithToken(chain, tokenRest);
        }
    }

    private Response handleRequestWithoutToken(Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request);
    }

    private Response handleRequestWithToken(Chain chain, TokenRest token) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(
                getRequestWithAuthorizationHeaders(request, token));
        if (NetworkUtils.isUnauthorizedCode(response.code())) {
            Timber.d("User is unauthorized, signing out");
            signOut();
        }
        return response;
    }

    private Request getRequestWithAuthorizationHeaders(Request request, TokenRest token) {
        return request.newBuilder()
                .addHeader("token", token.token())
                .method(request.method(), request.body())
                .build();
    }

    private void signOut() {
        preferenceController.clearSharedPreferences();
        LoginActivity.startActivity(context);
    }
}