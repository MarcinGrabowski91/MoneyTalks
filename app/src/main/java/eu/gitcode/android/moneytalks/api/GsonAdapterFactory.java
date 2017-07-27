package eu.gitcode.android.moneytalks.api;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
abstract class GsonAdapterFactory implements TypeAdapterFactory {

    private GsonAdapterFactory() {
        throw new AssertionError();
    }

    public static TypeAdapterFactory create() {
        return new AutoValueGson_GsonAdapterFactory();
    }

}