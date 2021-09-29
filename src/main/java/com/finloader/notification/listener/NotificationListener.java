package com.finloader.notification.listener;

import com.finloader.notification.model.Notification;
import com.finloader.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @SqsListener("top-organizations-queue")
    public void onCompaniesLoaded(List<Notification> message) {
        log.info("Trying to save notifications...");
        notificationService.saveAll(message);
    }
}
