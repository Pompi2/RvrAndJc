package com.hashik.rvrandjc.models;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationSubscriptionManager {
    private static NotificationSubscriptionManager manager;

    public static NotificationSubscriptionManager getInstance(){
        if (manager != null) {
            return manager;
        }
        return new NotificationSubscriptionManager();
    }

    public void subToAllChannels(){ //Subscribe to all channels
        for (String notCategory : Constants.NOT_CATEGORIES) {
            FirebaseMessaging.getInstance().subscribeToTopic(notCategory);
        }
    }

    public void unSubToAllChannels(){ //Remove all channel subscriptions
        for (String notCategory : Constants.NOT_CATEGORIES) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(notCategory);
        }
    }

    public void subToChannel(final String channel){
        unSubToAllChannels();//Clear all channels subscription
        FirebaseMessaging.getInstance().subscribeToTopic(channel); //Assign new channel subscription
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.NOT_CATEGORIES[8]); // Subscribing to all
    }

    public void unSubTOChannel(final String channel){
        unSubToAllChannels(); //Clear all channels subscription
        FirebaseMessaging.getInstance().unsubscribeFromTopic(channel); //Assign new channel subscription
    }
}
