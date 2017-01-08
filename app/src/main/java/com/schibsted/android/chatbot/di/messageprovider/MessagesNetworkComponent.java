package com.schibsted.android.chatbot.di.messageprovider;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = MessagesNetworkModule.class)
public interface MessagesNetworkComponent {

    Retrofit retrofit();
}
