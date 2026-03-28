package org.example.lld.Logger.service;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Level;
import org.example.lld.Logger.model.Message;
import org.example.lld.Logger.repository.ISink;
import org.example.lld.Logger.strategy.formatter.IFormatter;
import org.example.lld.Logger.strategy.handler.IMessageHandler;

import java.util.List;
import java.util.Map;

public class Logger {
    Configuration configuration;
    List<IMessageHandler> handlers;

    public Logger(Configuration configuration, List<IMessageHandler> handlers) {
        this.configuration = configuration;
        this.handlers = handlers;
    }

    public void log(Level level, String message) {
        Map<Level, List<ISink>> sinks = configuration.getSinks();

        IFormatter formatterType = configuration.getFormatterType();
        Message enrichedMessage = new Message(message, level);
        String convertedMessage = formatterType.format(enrichedMessage, handlers, configuration);

        List<ISink> sinkList = sinks.get(level);
        if(sinkList == null) {
            return;
        }
        for(ISink sink: sinkList) {
            if(level.getValue() >= configuration.getMinLogLevel().getValue())
                sink.log(convertedMessage);
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warn(String message) {
        log(Level.WARN, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
}
