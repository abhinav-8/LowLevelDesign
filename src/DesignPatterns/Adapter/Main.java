package DesignPatterns.Adapter;

import DesignPatterns.Adapter.Factory.NotificationServiceFactory;
import DesignPatterns.Adapter.Service.NotificationService;

public class Main {
    public static void main(String[] args) {
        NotificationService smsNotificationService = NotificationServiceFactory.getNotificationService("SMS");
        smsNotificationService.sendNotification("1234567890", "Hello World");

        NotificationService emailNotificationService = NotificationServiceFactory.getNotificationService("EMAIL");
        emailNotificationService.sendNotification("abcd@gmail.com", "Hello World");
    }
}
