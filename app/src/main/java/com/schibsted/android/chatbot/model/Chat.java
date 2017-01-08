package com.schibsted.android.chatbot.model;

        import java.io.Serializable;
        import android.os.Parcel;
        import android.os.Parcelable;
        import android.os.Parcelable.Creator;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Chat implements Serializable, Parcelable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("userImage_url")
    @Expose
    private String userImageUrl;
    @SerializedName("time")
    @Expose
    private String time;
    public final static Parcelable.Creator<Chat> CREATOR = new Creator<Chat>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Chat createFromParcel(Parcel in) {
            Chat instance = new Chat();
            instance.username = ((String) in.readValue((String.class.getClassLoader())));
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            instance.userImageUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.time = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Chat[] newArray(int size) {
            return (new Chat[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7982277064934423936L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getTime() {
        return time.replaceAll("h" , "");
    }

    public void setTime(String time) {
        this.time = time;
    }




    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(content);
        dest.writeValue(userImageUrl);
        dest.writeValue(time);
    }

    public int describeContents() {
        return 0;
    }

}