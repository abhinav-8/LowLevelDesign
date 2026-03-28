package org.example.lld.Logger.strategy.formatter;

import org.example.lld.Logger.model.Configuration;
import org.example.lld.Logger.strategy.handler.IMessageHandler;
import org.example.lld.Logger.model.Message;

import java.util.ArrayList;
import java.util.List;

public class SimpleFormatter implements IFormatter {

    @Override
    public String format(Message message, List<IMessageHandler> handlers, Configuration configuration) {
        String formatter = configuration.getFormat();
        int start = -1, end = 0, i = 0;
        List<String> keys = new ArrayList<>();

        while(i < formatter.length()) {
            if(formatter.charAt(i) == '[') start = i+1;

            while(start != -1 && i < formatter.length() && formatter.charAt(i) != ']') i++;
            end = i;

            if(start != -1)
                keys.add(formatter.substring(start, end));

            start=-1;
            i = end + 1;
        }

        return replaceKeys(keys, handlers, message, configuration);
    }

    private String replaceKeys(List<String> keys, List<IMessageHandler> handlers, Message message, Configuration configuration) {
        StringBuilder finalMessage = new StringBuilder();
        for(String key : keys) {
            for(IMessageHandler handler : handlers) {
                if(handler.doesSupport(key)) {
                   finalMessage.append("[ ").append(handler.gethandlerValue(message, configuration)).append(" ] ");
                }
            }
        }
        return finalMessage.toString();
    }
}
