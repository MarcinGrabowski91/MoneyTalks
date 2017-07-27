package eu.gitcode.android.moneytalks.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.gitcode.android.moneytalks.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

@Module
public class ApiModule {

    private final String baseUrl;

    private final HttpLoggingInterceptor.Level loggingLevel;

    public ApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
        this.loggingLevel = BuildConfig.DEBUG ? BODY : NONE;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHTTPClient(SessionInterceptor sessionInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(loggingLevel)).addInterceptor(sessionInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(GsonAdapterFactory.create())
                .registerTypeAdapter(DateTime.class, new DateTimeConverter())
                .create();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    @Named("NoAuthOkHttp")
    public OkHttpClient provideNoAuthOkHTTPClient() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(loggingLevel)).build();
    }

    @Singleton
    @Provides
    @Named("NoAuthRetrofit")
    public Retrofit provideNoAuthRetrofit(@Named("NoAuthOkHttp") OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    AuthApi provideLoginApi(@Named("NoAuthRetrofit") Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }
}