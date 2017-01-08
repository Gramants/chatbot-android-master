package com.schibsted.android.chatbot.di.messageprovider;


import com.schibsted.android.chatbot.CustomScope;
import com.schibsted.android.chatbot.presenter.impl.ChatActivityPresenter;
import dagger.Component;

@CustomScope
@Component(modules = MessagesApiModule.class, dependencies = MessagesNetworkComponent.class)
public interface MessagesApiComponent {
    void inject(ChatActivityPresenter chatPresenter);
}
