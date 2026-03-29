package org.example.lld.NotificationEngine.strategy;

import lombok.AllArgsConstructor;
import org.example.lld.NotificationEngine.model.User;
import org.example.lld.NotificationEngine.model.channelData.IChannelBasedData;
import org.example.lld.NotificationEngine.model.channelData.SmsChannelBasedData;
import org.example.lld.NotificationEngine.util.TPSms;

@AllArgsConstructor
public class SmsNotificationSender implements INotificationSender {

    private TPSms tpSms;

    @Override
    public void send(User user, IChannelBasedData iChannelBasedData) {
        SmsChannelBasedData sms = (SmsChannelBasedData) iChannelBasedData;
        //Template addition, formatting etc would be done here
        tpSms.sendSms(user.getPhone(), sms.getBody());
    }
}
