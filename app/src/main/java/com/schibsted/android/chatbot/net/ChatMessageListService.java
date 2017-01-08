package com.schibsted.android.chatbot.net;


import com.schibsted.android.chatbot.model.ChatMessage;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ChatMessageListService {
    @GET("/rocket-interview/chat.json")
   Observable<ChatMessage> fetchFilteredResults();

}