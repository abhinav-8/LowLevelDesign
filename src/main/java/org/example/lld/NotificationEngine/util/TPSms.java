package org.example.lld.NotificationEngine.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TPSms {
    public void sendSms(String phone, String message) {
        System.out.println("SMS sent to " + phone + ": " + message);
    }
}
