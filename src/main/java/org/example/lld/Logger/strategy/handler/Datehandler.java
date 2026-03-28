package org.example.lld.Logger.strategy.handler;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;

import java.util.Date;

public class Datehandler implements IMessageHandler {

    @Override
    public boolean doesSupport(String token) {
        return token.equals("timestamp");
    }

    @Override
    public String gethandlerValue(Message message, Configuration configuration) {
        return message.getTimestamp() == null ? (new Date()).toString() : message.getTimestamp();
    }
}
