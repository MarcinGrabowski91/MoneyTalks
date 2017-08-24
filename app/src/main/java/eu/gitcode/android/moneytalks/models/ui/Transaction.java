package eu.gitcode.android.moneytalks.models.ui;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.gitcode.android.moneytalks.models.api.TransactionRest;

@AutoValue
public abstract class Transaction implements Parcelable {
    public static Builder builder() {
        return new AutoValue_Transaction.Builder();
    }

    public static Transaction fromRest(TransactionRest transactionRest) {
        return Transaction.builder()
                .id(transactionRest.id())
                .subcategoryMonthId(transactionRest.subcategoryMonthId())
                .name(transactionRest.name())
                .date(transactionRest.date())
                .value(transactionRest.value())
                .build();
    }

    public static List<Transaction> fromRest(List<TransactionRest> transactionsRestList) {
        if (transactionsRestList != null) {
            List<Transaction> transactionsList = new ArrayList<>(transactionsRestList.size());
            for (TransactionRest transactionRest : transactionsRestList) {
                transactionsList.add(fromRest(transactionRest));
            }
            return transactionsList;
        }
        return Collections.emptyList();
    }

    public abstract Long id();

    public abstract Long subcategoryMonthId(); //TODO change to categoryId

    public abstract String name();

    public abstract DateTime date();

    public abstract Float value();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder subcategoryMonthId(Long subcategoryMonthId);

        public abstract Builder name(String name);

        public abstract Builder date(DateTime date);

        public abstract Builder value(Float value);

        public abstract Transaction build();
    }
}