package eu.gitcode.android.moneytalks.ui.feature.notifications;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.enumeration.NotificationPagesEnum;
import eu.gitcode.android.moneytalks.ui.common.base.BaseFragment;

public class NotificationsFragment extends BaseFragment {
    public static final String TAG = NotificationsFragment.class.getSimpleName();

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    NotificationsPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notifications_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        adapter = new NotificationsPagerAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(NotificationPagesEnum.MESSAGES.ordinal());
        viewPager.setOffscreenPageLimit(NotificationsPagerAdapter.PAGE_COUNT);
    }
}