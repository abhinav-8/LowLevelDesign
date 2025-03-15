package SOLID.Example2.Improved;

import java.util.List;

public class NotificationDriver {
    public void sendNotification(List<Notification> notifications, String phone, String email, String message) {
        for (Notification notification : notifications) {
            notification.sendNotification(phone, email, message);
        }
    }
}
