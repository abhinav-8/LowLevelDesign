package org.example.lld.Logger.strategy.handler;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;

public interface IMessageHandler {
    boolean doesSupport(String token);
    String gethandlerValue(Message message, Configuration configuration);
}
