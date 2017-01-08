package com.schibsted.android.chatbot.di.messageprovider;


import com.schibsted.android.chatbot.CustomScope;
import com.schibsted.android.chatbot.net.ChatMessageListService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class MessagesApiModule {

    @Provides
    @CustomScope
    ChatMessageListService provideChatMessageListService (Retrofit retrofit) {
        return retrofit.create(ChatMessageListService.class);
    }
}
