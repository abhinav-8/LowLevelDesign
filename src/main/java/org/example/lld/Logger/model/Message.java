package org.example.lld.Logger.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String content;
    private Level level;
    private String timestamp;
    private String threadName;
    private String namespace;

    public Message(String content, Level level) {
        this.content = content;
        this.level = level;
    }
}
