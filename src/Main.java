import DesignPatterns.Singleton.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        //Call singleton
        Logger logger = Logger.getLogger();
        logger.log("siiiiiiiiiu");
    }
}