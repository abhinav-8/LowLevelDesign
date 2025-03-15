package SOLID.Example2.Improved;

public class SmsNotification implements Notification {

    @Override
    public  void sendNotification(String phone, String email, String message) {
        System.out.println("SMS Notification sent successfully to " + phone);
    }
}
