package com.tessaro.notification.controller;

import com.tessaro.clients.notification.type.request.NotificationRequest;
import com.tessaro.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @PostMapping()
    public void sendNotification (@RequestBody NotificationRequest notificationRequest){
        log.info("Notification - NotificationController - sendNotification with follow data: {}.", notificationRequest);
        notificationService.sendNotification(notificationRequest);
        log.info("Notification - NotificationController - notification was sent.");
    };

}
