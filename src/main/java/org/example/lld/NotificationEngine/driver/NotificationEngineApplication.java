package org.example.lld.NotificationEngine.driver;

import org.example.lld.NotificationEngine.dto.ChannelInput;
import org.example.lld.NotificationEngine.dto.NotificationRequestDTO;
import org.example.lld.NotificationEngine.model.channelData.EmailChannelBasedData;
import org.example.lld.NotificationEngine.model.channelData.SmsChannelBasedData;
import org.example.lld.NotificationEngine.service.NotificationService;

import java.util.List;

public class NotificationEngineApplication {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        String id1 = notificationService.addUser("abhi.ak9@gmail.com", "9415720215");
        String id2 = notificationService.addUser("avi.ak9@gmail.com", "9415520415");

        NotificationRequestDTO request1 = new NotificationRequestDTO(
                id1,
                "LIKE_POST",
                List.of(
                        new ChannelInput("SMS", new SmsChannelBasedData("Someone liked your post")),
                        new ChannelInput("EMAIL", new EmailChannelBasedData("New Like", "Someone liked your post"))
                )
        );


        NotificationRequestDTO request2 = new NotificationRequestDTO(
                id2,
                "LIKE_POST",
                List.of(
                        new ChannelInput("SMS", new SmsChannelBasedData("Someone liked your post")),
                        new ChannelInput("EMAIL", new EmailChannelBasedData("New Like", "Someone liked your post"))
                )
        );

        notificationService.send(request1);
        notificationService.send(request2);

    }
}
