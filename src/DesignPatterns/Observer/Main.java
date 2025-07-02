package DesignPatterns.Observer;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel("CR7");
        Subscriber abhinav = new EmailSubscriber("abhinav@gmail.com");
        Subscriber avinash = new MobileSubscriber("1234567890");

        channel.addSubscriber(abhinav);
        channel.addSubscriber(avinash);

        channel.uploadVideo("GOAT");
    }
}
