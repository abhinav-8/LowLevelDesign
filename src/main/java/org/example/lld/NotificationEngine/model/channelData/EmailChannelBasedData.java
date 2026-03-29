package org.example.lld.NotificationEngine.model.channelData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailChannelBasedData implements IChannelBasedData {
    private String body;
    private String subject;
}
