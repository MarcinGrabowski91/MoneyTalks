package eu.gitcode.android.moneytalks.ui.feature.notifications.logs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Notification;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.notifications.adapter.NotificationsAdapter;

public class LogsFragment extends BaseMvpFragment<LogsContract.View,
        LogsContract.Presenter> implements LogsContract.View {
    public static final String TAG = LogsFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    NotificationsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logs_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().handleLogsData();
    }

    @Override
    @NonNull
    public LogsFragmentPresenter createPresenter() {
        LogsComponent component = App.getAppComponent(getContext()).getLogsComponent();
        component.inject(this);
        return component.getLogsPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showLogsData() {
        //TODO load categories data from the server
        List<Notification> notificationsList = new ArrayList<>();
        notificationsList.add(Notification.builder().name("Kupiłeś spółkę CDP za 350zł")
                .date(DateTime.now().minusDays(3)).id(1L).build());
        notificationsList.add(Notification.builder().name("Kupiłeś Piłka do koszykówki za 25zł")
                .date(DateTime.now().minusWeeks(1)).id(1L).build());
        notificationsList.add(Notification.builder().name("Sprzedałeś akcje LEN za 1400zł")
                .date(DateTime.now().minusWeeks(2)).id(1L).build());
        adapter.setNotifications(notificationsList);
    }

    private void setupRecyclerView() {
        adapter = new NotificationsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}