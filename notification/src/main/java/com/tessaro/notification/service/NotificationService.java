package com.tessaro.notification.service;

import com.tessaro.clients.notification.type.request.NotificationRequest;
import com.tessaro.notification.model.Notification;
import com.tessaro.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService (NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification (NotificationRequest notificationRequest) {
        log.info("Notification - Service - sendNotification with follow data: {}.", notificationRequest);
        Notification savedNotification = notificationRepository.save(
                Notification.builder()
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .sender("CASSIO")
                        .toCustomerId(notificationRequest.toCustomerId())
                        .build());
        log.info("Notification - Service - notification was sent.");
    }
}
