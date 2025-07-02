package DesignPatterns.Observer;

public class MobileSubscriber implements Subscriber{
    private final String mobileNo;
    public MobileSubscriber(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.println("Sms to " + mobileNo + " : New Video Uploaded -- " + videoTitle);
    }
}
