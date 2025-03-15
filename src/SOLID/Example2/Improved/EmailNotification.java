package SOLID.Example2.Improved;

public class EmailNotification implements Notification {

    @Override
    public void sendNotification(String phone, String email, String message) {
        System.out.println("Email Notification sent to " + email);
    }
}
