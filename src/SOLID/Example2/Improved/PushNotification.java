package SOLID.Example2.Improved;

public class PushNotification implements Notification {

    @Override
    public void sendNotification(String phone, String email, String message) {
        System.out.println("Push Notification sent successfully to " + email);
    }
}
