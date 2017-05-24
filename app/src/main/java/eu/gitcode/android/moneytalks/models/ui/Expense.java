package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

@AutoValue
public abstract class Expense implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Expense.Builder();
    }

    @Nullable
    public abstract Long id();

    public abstract String title();

    public abstract Float cost();

    @Nullable
    public abstract String description();

    public abstract DateTime date();

    @Nullable
    public abstract Category category();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder cost(Float cost);

        public abstract Builder description(String description);

        public abstract Builder date(DateTime date);

        public abstract Builder category(Category category);

        public abstract Expense build();
    }
}