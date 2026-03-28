package org.example.lld.Logger.strategy.handler;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;

public class ThreadNamehandler implements IMessageHandler {
    
    @Override
    public boolean doesSupport(String token) {
        return token.equals("threadName");
    }

    @Override
    public String gethandlerValue(Message message, Configuration configuration) {
        return message.getThreadName() == null ?  Thread.currentThread().getName() : message.getThreadName();
    }

}
