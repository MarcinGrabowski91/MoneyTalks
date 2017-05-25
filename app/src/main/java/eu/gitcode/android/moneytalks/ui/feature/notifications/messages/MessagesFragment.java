package eu.gitcode.android.moneytalks.ui.feature.notifications.messages;

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

public class MessagesFragment extends BaseMvpFragment<MessagesContract.View,
        MessagesContract.Presenter> implements MessagesContract.View {
    public static final String TAG = MessagesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    NotificationsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.messages_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().handleMessagesData();
    }

    @Override
    @NonNull
    public MessagesFragmentPresenter createPresenter() {
        MessagesComponent component = App.getAppComponent(getContext()).getMessagesComponent();
        component.inject(this);
        return component.getMessagesPresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showMessagesData() {
        //TODO load categories data from the server
        List<Notification> notificationsList = new ArrayList<>();
        notificationsList.add(Notification.builder().name("Minął termin Zapłacić za dom")
                .date(DateTime.now().minusWeeks(2)).id(1L).build());
        notificationsList.add(Notification.builder().name("Minął termin Opłacić samochód")
                .date(DateTime.now().minusDays(2)).id(1L).build());
        notificationsList.add(Notification.builder().name("Zbliża się termin Oddać pieniądze Dawidowi!")
                .date(DateTime.now().plusDays(3)).id(1L).build());

        adapter.setNotifications(notificationsList);
    }

    private void setupRecyclerView() {
        adapter = new NotificationsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}