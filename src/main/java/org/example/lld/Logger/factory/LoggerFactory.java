package org.example.lld.Logger.factory;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Level;
import org.example.lld.Logger.service.Logger;
import org.example.lld.Logger.strategy.handler.IMessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerFactory {
    private static final Map<String, Logger> loggers = new HashMap<>();

    public static Logger getLogger(String loggerName, List<IMessageHandler> handlers, Configuration config) {
        return loggers.putIfAbsent(loggerName, loggers.put(loggerName, new Logger(config, handlers)));
    }
}
