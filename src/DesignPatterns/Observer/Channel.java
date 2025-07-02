package DesignPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String channelName;
    List<Subscriber> subscriberList = new ArrayList<>();
    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    public void uploadVideo(String videoTitle) {
        System.out.println("Channel " + channelName + " uploaded new video: " + videoTitle);
        notifySubscribers(videoTitle);
    }
    private void notifySubscribers(String videoTitle) {
        for (Subscriber subscriber : subscriberList) {
            subscriber.notify(videoTitle);
        }
    }
}
