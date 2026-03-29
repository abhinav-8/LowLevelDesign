package org.example.lld.NotificationEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.NotificationEngine.model.channelData.IChannelBasedData;

@AllArgsConstructor
@Getter
@Setter
public class ChannelInput {
    private String type;
    private IChannelBasedData channelBasedData;
}
