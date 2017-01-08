package com.schibsted.android.chatbot.presenter.impl;


import com.schibsted.android.chatbot.App;
import com.schibsted.android.chatbot.model.Chat;
import com.schibsted.android.chatbot.model.ChatMessage;
import com.schibsted.android.chatbot.net.ChatMessageListService;
import com.schibsted.android.chatbot.presenter.ChatPresenterContract;
import com.schibsted.android.chatbot.presenter.ChatViewContract;
import com.schibsted.android.chatbot.view.ChatActivity;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;


public class ChatActivityPresenter implements ChatPresenterContract {

    @Inject
    ChatMessageListService chatWebService;


    private Subscription subscriptionmessages;
    private ChatViewContract callback;

    public ChatActivityPresenter(ChatActivity chatactivity) {
        this.callback=chatactivity;
        ((App) chatactivity.getApplication()).getMessagesApiComponent().inject(this);

    }



    @Override
    public void CurrentMessages()  {

        callback.startLoadMessages();

        if (subscriptionmessages != null) subscriptionmessages.unsubscribe();
        subscriptionmessages = chatWebService.fetchFilteredResults()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ChatMessage>() {

                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable error) {}

                    @Override
                    public void onNext(ChatMessage chatMessage) {
                        callback.showChatMessages(chatMessage.getChats());
                    }

                });
    }

}
