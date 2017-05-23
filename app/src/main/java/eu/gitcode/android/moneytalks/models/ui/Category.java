package eu.gitcode.android.moneytalks.models.ui;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Category {
    public static Builder builder() {
        return new AutoValue_Category.Builder();
    }

    @Nullable
    public abstract Long id();

    public abstract String name();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Category build();
    }
}