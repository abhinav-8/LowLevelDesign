package org.example.lld.NotificationEngine.service;

import lombok.NoArgsConstructor;
import org.example.lld.NotificationEngine.dto.ChannelInput;
import org.example.lld.NotificationEngine.dto.NotificationRequestDTO;
import org.example.lld.NotificationEngine.factory.NotificationSenderFactory;
import org.example.lld.NotificationEngine.model.ChannelType;
import org.example.lld.NotificationEngine.model.User;
import org.example.lld.NotificationEngine.strategy.INotificationSender;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
public class NotificationService {
    private final Map<String, User> users = new HashMap<>();
    public String addUser(String email, String phone) {
        User user = new User(UUID.randomUUID().toString(), phone, email);
        users.put(user.getId(), user);
        return user.getId();
    }

    public void send(NotificationRequestDTO notification) {
        User receiver = users.get(notification.getReceiverId());
        if(receiver == null) {
            throw new RuntimeException("Receiver id not found");
        }

        for(ChannelInput channelInput : notification.getInputList()) {
            String type = channelInput.getType();
            ChannelType channelType = ChannelType.valueOf(type);
            INotificationSender  notificationSender = NotificationSenderFactory.getNotificationSender(channelType);
            notificationSender.send(receiver, channelInput.getChannelBasedData());
        }
    }
}
