package SOLID.Example2.Problem;

import java.util.List;

/*
This class is problematic as this will change everytime a notification
type is introduced.

It is violating OCP(Open Closed Principle)
It states that:
"A class should be open to extension but closed to modification".
 */
public class NotificationDriver {

    public void sendNotification(List<NotificationType> notifications, String message, String phone, String email) {
        for (NotificationType notification : notifications) {
            switch (notification) {
                case NotificationType.EMAIL:
                    notification.sendEmailNotification(message, email);
                    break;
                case NotificationType.SMS:
                    notification.sendSMSNotification(message, phone);
                    break;
                case NotificationType.PUSH:
                    notification.sendPushNotification(message, phone);
                    break;
                case NotificationType.WHATSAPP:
                    notification.sendWhatsAppNotification(message, phone);
                    break;
            }
        }
    }
}
