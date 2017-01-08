package com.schibsted.android.chatbot.persistence;


import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.schibsted.android.chatbot.model.Chat;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class PersistentStorageProxyImpl implements PersistentStorageProxy {
    private static final String ISLOGGED = "ISLOGGED";
    private static final String LOGGEDAS = "LOGGEDAS";
    private static final String MYMESSAGES = "MYMESSAGES";
    private SharedPreferences mPreferences;


    public PersistentStorageProxyImpl(SharedPreferences sharedPreferences) {
        this.mPreferences=sharedPreferences;
    }


    @Override
    public ArrayList<Chat> getMyMessages() {
        Gson gson = new Gson();
        String jsonText = mPreferences.getString(MYMESSAGES, null);
        Type type = new TypeToken<ArrayList<Chat>>() {}.getType();
        ArrayList<Chat> array = gson.fromJson(jsonText, type);
        return array;
    }

    @Override
    public void setMyMessages(ArrayList mymessages) {
        Gson gson = new Gson();
        ArrayList<Chat> textList = new ArrayList<Chat>();
        textList.addAll(mymessages);
        String jsonText = gson.toJson(textList);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(MYMESSAGES, jsonText);
        editor.apply();
    }

    @Override
    public void onLogout() {

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString(MYMESSAGES, null);
        editor.apply();

        editor.putBoolean(ISLOGGED, false);
        editor.apply();

        editor.putString(LOGGEDAS, "");
        editor.apply();

    }

    @Override
    public String getUserName() {
        return mPreferences.getString(LOGGEDAS, "");
    }

    @Override
    public boolean isLogged() {
        return mPreferences.getBoolean(ISLOGGED, false);
    }

    @Override
    public void setUserLogged(String user) {

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putBoolean(ISLOGGED, true);
        editor.apply();

        editor.putString(LOGGEDAS, user);
        editor.apply();

    }
}
