package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

@AutoValue
public abstract class Notification implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Notification.Builder();
    }

    public abstract Long id();

    public abstract String name();

    public abstract DateTime date();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder date(DateTime date);

        public abstract Notification build();
    }
}