package eu.gitcode.android.moneytalks.ui.feature.notifications.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Notification;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private List<Notification> notificationsList = new ArrayList<>();

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new NotificationViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        Notification notification = notificationsList.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public void setNotifications(List<Notification> notificationsList) {
        this.notificationsList = notificationsList;
        notifyDataSetChanged();
    }
}