package eu.gitcode.android.moneytalks.ui.common.base;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MvpPresenterRest<V extends MvpView> extends MvpPresenter<V> {
    void handleErrorResponse(Throwable throwable, String errorText);
}
