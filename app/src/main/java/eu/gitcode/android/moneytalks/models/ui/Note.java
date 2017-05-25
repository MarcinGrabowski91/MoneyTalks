package eu.gitcode.android.moneytalks.models.ui;


import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

@AutoValue
public abstract class Note implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Note.Builder();
    }

    @Nullable
    public abstract Long id();

    public abstract String title();

    @Nullable
    public abstract String content();

    public abstract DateTime date();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder content(String content);

        public abstract Builder date(DateTime date);

        public abstract Note build();
    }
}
