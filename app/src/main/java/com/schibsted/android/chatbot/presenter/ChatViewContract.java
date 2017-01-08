package com.schibsted.android.chatbot.presenter;


import com.schibsted.android.chatbot.model.Chat;
import java.util.List;

public interface ChatViewContract {
    void startLoadMessages();
    void showChatMessages(List<Chat> messageList);

}
