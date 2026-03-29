package org.example.lld.NotificationEngine.strategy;

import lombok.AllArgsConstructor;
import org.example.lld.NotificationEngine.model.User;
import org.example.lld.NotificationEngine.model.channelData.EmailChannelBasedData;
import org.example.lld.NotificationEngine.model.channelData.IChannelBasedData;
import org.example.lld.NotificationEngine.util.TPEmail;

@AllArgsConstructor
public class EmailNotificationSender implements INotificationSender {
    private TPEmail tpEmail;

    @Override
    public void send(User user, IChannelBasedData iChannelBasedData) {
        EmailChannelBasedData email  = (EmailChannelBasedData) iChannelBasedData;
        //Template addition, formatting etc would be done here
        tpEmail.sendEmail(user.getEmail(), email.getSubject(), email.getBody());
    }
}
