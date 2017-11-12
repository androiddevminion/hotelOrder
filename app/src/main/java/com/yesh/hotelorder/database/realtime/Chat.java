package com.yesh.hotelorder.database.realtime;

/**
 * Created by yesh on 11/5/17.
 */

import com.google.firebase.database.IgnoreExtraProperties;
import com.yesh.hotelorder.database.AbstractChat;

@IgnoreExtraProperties
public class Chat extends AbstractChat {

    private String mName;
    private String mMessage;
    private String mUid;

    public Chat() {
        // Needed for Firebase
    }

    public Chat(String name, String message, String uid) {
        mName = name;
        mMessage = message;
        mUid = uid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }
}