package DesignPatterns.Observer;

public class EmailSubscriber implements Subscriber{
    private final String email;
    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.println("Email to " + email + " : New Video Uploaded -- " + videoTitle);
    }
}
