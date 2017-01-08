package com.schibsted.android.chatbot.persistence;

import java.util.ArrayList;

public interface PersistentStorageProxy {


    ArrayList getMyMessages();
    void setMyMessages(ArrayList mymessages);
    void onLogout();

    String getUserName();

    boolean isLogged();

    void setUserLogged(String user);
}
