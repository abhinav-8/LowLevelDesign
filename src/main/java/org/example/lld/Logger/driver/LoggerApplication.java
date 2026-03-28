package org.example.lld.Logger.driver;

import org.example.lld.Logger.factory.LoggerFactory;
import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.model.Level;
import org.example.lld.Logger.repository.ConsoleSink;
import org.example.lld.Logger.repository.ISink;
import org.example.lld.Logger.service.Logger;
import org.example.lld.Logger.strategy.formatter.SimpleFormatter;
import org.example.lld.Logger.strategy.handler.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerApplication {
    static void main(String[] args) {
        Map<Level, List<ISink>> sinks = new HashMap<>();
        sinks.put(Level.INFO, List.of(new ConsoleSink()));
        sinks.put(Level.WARN, List.of(new ConsoleSink()));
        sinks.put(Level.ERROR, List.of(new ConsoleSink()));
        sinks.put(Level.DEBUG, List.of(new ConsoleSink()));

        String format = "[timestamp] [level] [namespace] [threadName] [message]";

        Configuration configuration = new Configuration(Level.INFO, sinks, format, new SimpleFormatter(), "Lending");
        Configuration configuration2 = new Configuration(Level.INFO, sinks, format, new SimpleFormatter(), "Payment-Service");
        Logger log1 = LoggerFactory.getLogger("Payment", List.of(new Datehandler(), new Levelhandler(), new Messagehandler(), new ThreadNamehandler(), new NamespaceHandler()), configuration);
        Logger log2 = LoggerFactory.getLogger("Payment", List.of(new Datehandler(), new Levelhandler(), new Messagehandler(), new ThreadNamehandler(), new NamespaceHandler()), configuration);
        log1.info("Testing logging infooooo");
        log1.debug("Testing logging debugging");
        log2.warn("Testing logging warning");


        Logger log3 = LoggerFactory.getLogger("Payment-Service", List.of(new Datehandler(), new Levelhandler(), new Messagehandler(), new ThreadNamehandler(), new NamespaceHandler()), configuration2);
        log3.info("1 Testing logging infooooo");
        log1.debug("2 Testing logging debugging");
        log3.warn("3 Testing logging warning");

    }
}
