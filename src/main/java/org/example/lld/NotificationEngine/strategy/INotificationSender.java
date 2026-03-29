package org.example.lld.NotificationEngine.strategy;

import org.example.lld.NotificationEngine.model.User;
import org.example.lld.NotificationEngine.model.channelData.IChannelBasedData;

public interface INotificationSender {
    void send(User user, IChannelBasedData iChannelBasedData);
}
