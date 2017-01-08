package com.schibsted.android.chatbot.di.storageprovider;


import com.schibsted.android.chatbot.view.ChatActivity;
import com.schibsted.android.chatbot.view.LoginActivity;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ChatActivity chat);
    void inject(LoginActivity login);
}