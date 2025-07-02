package DesignPatterns.Adapter.Service;

import DesignPatterns.Adapter.API.ThirdPartySmsAPI;

public class SmsNotificationAdaptor implements NotificationService {
    private ThirdPartySmsAPI thirdPartySmsAPI;

    public SmsNotificationAdaptor(ThirdPartySmsAPI thirdPartySmsAPI) {
        this.thirdPartySmsAPI = thirdPartySmsAPI;
    }

    @Override
    public void sendNotification(String to, String message) {
        thirdPartySmsAPI.sendText(to, message);
    }
}