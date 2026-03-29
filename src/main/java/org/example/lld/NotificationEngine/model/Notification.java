package org.example.lld.NotificationEngine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.lld.NotificationEngine.model.channelData.IChannelBasedData;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Notification {
    private NotificationType  notificationType;
    private User receiver;
    private Map<ChannelType, IChannelBasedData> channelBasedDataMap;
}
