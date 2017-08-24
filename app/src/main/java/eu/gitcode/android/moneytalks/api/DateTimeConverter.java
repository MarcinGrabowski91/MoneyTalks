package eu.gitcode.android.moneytalks.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.lang.reflect.Type;

class DateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getMillis());
    }

    @Override
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return new DateTime(json.getAsLong(), DateTimeZone.UTC);
    }
}