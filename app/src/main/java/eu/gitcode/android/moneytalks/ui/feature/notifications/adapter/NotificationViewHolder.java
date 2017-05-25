package eu.gitcode.android.moneytalks.ui.feature.notifications.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Notification;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class NotificationViewHolder extends BaseViewHolder<Notification> {

    @BindView(R.id.title_txt)
    TextView titleTxt;

    @BindView(R.id.date_txt)
    TextView dateTxt;

    public NotificationViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_view_holder, parent, false));
    }

    @Override
    public void bind(Notification item) {
        titleTxt.setText(item.name());
        dateTxt.setText(DateUtils.getRelativeDate(item.date()));
    }
}