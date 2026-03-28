package org.example.lld.Logger.strategy.handler;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;

public class NamespaceHandler implements IMessageHandler {
    @Override
    public boolean doesSupport(String token) {
        return token.equals("namespace");
    }

    @Override
    public String gethandlerValue(Message message, Configuration configuration) {
        return configuration.getNamespace() == null ? "" : configuration.getNamespace();
    }
}
