package org.example.lld.NotificationEngine.factory;

import org.example.lld.NotificationEngine.model.ChannelType;
import org.example.lld.NotificationEngine.strategy.EmailNotificationSender;
import org.example.lld.NotificationEngine.strategy.INotificationSender;
import org.example.lld.NotificationEngine.strategy.SmsNotificationSender;
import org.example.lld.NotificationEngine.util.TPEmail;
import org.example.lld.NotificationEngine.util.TPSms;

public class NotificationSenderFactory {
    public static INotificationSender getNotificationSender(ChannelType type) {
        switch (type) {
            case SMS -> {
                return new SmsNotificationSender(new TPSms());
            }
            case EMAIL -> {
                return new EmailNotificationSender(new TPEmail());
            }
        }
        throw new RuntimeException("Not support channel type: " + type);
    }
}
