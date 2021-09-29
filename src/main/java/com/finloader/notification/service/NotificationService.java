package com.finloader.notification.service;

import com.finloader.notification.model.Notification;
import com.finloader.notification.repo.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public boolean saveAll(List<Notification> notification) {
        return !notificationRepository.saveAll(notification).isEmpty();
    }

    public Notification findById(String id) throws ChangeSetPersister.NotFoundException {
        return notificationRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void deleteAll() {
        notificationRepository.deleteAll();
    }
}
