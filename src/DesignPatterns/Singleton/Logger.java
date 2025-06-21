package DesignPatterns.Singleton;

import java.util.Date;

public class Logger {

    //Private static variable to hold single instance
    private static Logger logger;

    //Private constructor to prevent instantiation
    private Logger() {}

    //Public method to provide access to instance
    public static Logger getLogger() {
        if(logger == null) {
            synchronized (Logger.class) {
                if(logger == null) {
                    logger = new Logger(); // Create new instance if it doesn't exist
                }
            }
        }
        return logger; //Return existing instance
    }

    public void log(String msg) {
        System.out.println((new Date()).toString() + " " +  msg);
    }
}
