package com.schibsted.android.chatbot.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatMessage implements Serializable, Parcelable
{

    @SerializedName("chats")
    @Expose
    private List<Chat> chats = null;
    public final static Parcelable.Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ChatMessage createFromParcel(Parcel in) {
            ChatMessage instance = new ChatMessage();
            in.readList(instance.chats, (Chat.class.getClassLoader()));
            return instance;
        }

        public ChatMessage[] newArray(int size) {
            return (new ChatMessage[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5678797216919284008L;

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }




    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(chats);
    }

    public int describeContents() {
        return 0;
    }

}