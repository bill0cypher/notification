package com.finloader.notification.listener;

import com.finloader.notification.model.Notification;
import com.finloader.notification.service.NotificationService;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class NotificationListener {

    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @SqsListener(value = "top-organizations-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onCompaniesLoaded(List<Notification> message) throws ExecutionException, InterruptedException {
        notificationService.saveAll(message);
    }
}
