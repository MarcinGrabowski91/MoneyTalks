package eu.gitcode.android.moneytalks.ui.common.base;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import eu.gitcode.android.moneytalks.utils.NetworkUtils;
import timber.log.Timber;

public class MvpBasePresenterRest<V extends MvpViewRest> extends MvpNullObjectBasePresenter<V> {

    public void handleErrorResponse(Throwable throwable, String errorText) {
        String error = throwable != null
                ? String.format("%s:, %s", errorText, throwable.getLocalizedMessage())
                : errorText;
        Timber.d(error);
        getView().showViewOnError(NetworkUtils.getExceptionText(throwable));
    }
}
