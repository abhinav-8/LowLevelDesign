package org.example.lld.Logger.strategy.handler;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;

public class Levelhandler implements IMessageHandler{

    @Override
    public boolean doesSupport(String token) {
        return token.equals("level");
    }

    @Override
    public String gethandlerValue(Message message, Configuration configuration) {
        return message.getLevel().name();
    }
}
