package com.example.utils;

import org.bson.types.ObjectId;

public class NotificationIdGenerator {

    public static String generate(){
        return new ObjectId().toString();
    }
}
