package org.example.lld.Logger.strategy.formatter;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Message;
import org.example.lld.Logger.strategy.handler.IMessageHandler;

import java.util.List;

public interface IFormatter {
    String format(Message message, List<IMessageHandler> handlers, Configuration configuration);
}
