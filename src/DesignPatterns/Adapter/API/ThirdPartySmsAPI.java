package DesignPatterns.Adapter.API;

public class ThirdPartySmsAPI {
    public void sendText(String phoneNumber, String smsContent) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + smsContent);
    }
}
