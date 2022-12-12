package com.tessaro.notification.consumer;

import com.tessaro.clients.notification.type.request.NotificationRequest;
import com.tessaro.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void notificationConsumer (NotificationRequest notificationRequest){
        log.info("Notification - NotificationController - sendNotification with follow data: {}.", notificationRequest);
        notificationService.sendNotification(notificationRequest);
        log.info("Notification - NotificationController - notification was sent.");
    };
}
