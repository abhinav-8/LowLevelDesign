package org.example.lld.NotificationEngine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class NotificationRequestDTO {
    private String receiverId;
    private String notificationType;
    private List<ChannelInput> inputList;
}
