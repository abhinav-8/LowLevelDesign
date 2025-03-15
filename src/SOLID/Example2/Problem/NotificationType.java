package SOLID.Example2.Problem;

public enum NotificationType {
    SMS,
    EMAIL,
    PUSH,
    WHATSAPP;

    public void sendSMSNotification(String phoneNumber, String message) {
        System.out.println("Sms sent successfully");
    }

    public void sendEmailNotification(String email, String message) {
        System.out.println("Email sent successfully");
    }

    public void sendPushNotification(String phoneNumber, String message) {
        System.out.println("Push sent successfully");
    }

    public void sendWhatsAppNotification(String phoneNumber, String message) {
        System.out.println("Whats app sent successfully");
    }
}
