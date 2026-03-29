package org.example.lld.NotificationEngine.model.channelData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SmsChannelBasedData implements IChannelBasedData {
    private String body;
}
