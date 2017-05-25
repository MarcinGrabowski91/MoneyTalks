package eu.gitcode.android.moneytalks.ui.feature.notifications;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import eu.gitcode.android.moneytalks.enumeration.NotificationPagesEnum;
import eu.gitcode.android.moneytalks.ui.feature.notifications.logs.LogsFragment;
import eu.gitcode.android.moneytalks.ui.feature.notifications.messages.MessagesFragment;

public class NotificationsPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_COUNT = 2;
    private final SparseArray<Fragment> notificationFragments;
    private final Context context;

    public NotificationsPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
        notificationFragments = new SparseArray<>(NotificationPagesEnum
                .getNamesList(context.getResources()).size());
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (NotificationPagesEnum.values()[position]) {
            case MESSAGES:
                return new MessagesFragment();
            case LOGS:
                return new LogsFragment();
            default:
                throw new IllegalArgumentException("Wrong fragment page number");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return NotificationPagesEnum.getNamesList(context.getResources()).get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        notificationFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        notificationFragments.remove(position);
    }
}