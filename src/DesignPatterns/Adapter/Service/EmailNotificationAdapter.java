package DesignPatterns.Adapter.Service;

import DesignPatterns.Adapter.API.ThirdPartyEmailAPI;

public class EmailNotificationAdapter implements NotificationService {
    private ThirdPartyEmailAPI emailAPI;

    public EmailNotificationAdapter(ThirdPartyEmailAPI emailAPI) {
        this.emailAPI = emailAPI;
    }

    @Override
    public void sendNotification(String to, String message) {
        emailAPI.sendEmail(to, message);
    }
}
