package DesignPatterns.Adapter.API;

public class ThirdPartyEmailAPI {
    public void sendEmail(String email, String body) {
        System.out.println("Sending email to " + email);
    }
}
