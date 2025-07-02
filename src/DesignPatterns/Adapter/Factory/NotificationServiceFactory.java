package DesignPatterns.Adapter.Factory;

import DesignPatterns.Adapter.API.ThirdPartyEmailAPI;
import DesignPatterns.Adapter.API.ThirdPartySmsAPI;
import DesignPatterns.Adapter.Service.EmailNotificationAdapter;
import DesignPatterns.Adapter.Service.NotificationService;
import DesignPatterns.Adapter.Service.SmsNotificationAdaptor;

public class NotificationServiceFactory {
    public static NotificationService getNotificationService(String type) {
        if(type.equalsIgnoreCase("sms")){
            return new SmsNotificationAdaptor(new ThirdPartySmsAPI());
        }
        if(type.equalsIgnoreCase("email")){
            return new EmailNotificationAdapter((new ThirdPartyEmailAPI()));
        }

        throw new IllegalArgumentException("Invalid Notification Type");
    }
}
