package eu.gitcode.android.moneytalks.utils;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class RxTransformers {

    private RxTransformers() {
        throw new AssertionError();
    }

    /**
     * Apply schedulers settings for observable: subscribe on io thread, observe on ui thread.
     *
     * @param <T> Type of result observable.
     * @return Transformed observable.
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Apply schedulers settings for completable: subscribe on io thread, observe on ui thread.
     *
     * @return Transformed completable.
     */
    public static Completable.Transformer applyCompletableSchedulers() {
        return completable -> completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Apply schedulers settings for completable: subscribe on io thread, observe on ui thread.
     *
     * @return Transformed completable.
     */
    public static <T> Single.Transformer<T, T> applySingleSchedulers() {
        return single -> single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}