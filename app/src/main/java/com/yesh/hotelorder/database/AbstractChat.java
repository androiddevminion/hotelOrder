package com.yesh.hotelorder.database;

/**
 * Created by yesh on 11/5/17.
 */

/**
 * Common interface for chat messages, helps share code between RTDB and Firestore examples.
 */
public abstract class AbstractChat {

    public abstract String getName();

    public abstract String getMessage();

    public abstract String getUid();

}