package com.tessaro.clients.notification;

import com.tessaro.clients.notification.type.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping(path = "api/v1/notification")
    public void sendNotification (
          NotificationRequest notificationRequest
    );
}