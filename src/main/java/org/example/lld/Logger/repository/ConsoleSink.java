package org.example.lld.Logger.repository;

public class ConsoleSink implements ISink {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
