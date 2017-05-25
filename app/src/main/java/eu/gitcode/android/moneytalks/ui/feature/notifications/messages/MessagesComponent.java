package eu.gitcode.android.moneytalks.ui.feature.notifications.messages;

import dagger.Subcomponent;
import eu.gitcode.android.moneytalks.dagger.scopes.FragmentScope;

@FragmentScope
@Subcomponent
public interface MessagesComponent {

    void inject(MessagesFragment messagesFragment);

    MessagesFragmentPresenter getMessagesPresenter();
}