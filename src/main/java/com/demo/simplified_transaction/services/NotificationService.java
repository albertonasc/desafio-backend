package com.demo.simplified_transaction.services;

import com.demo.simplified_transaction.infrastructure.clients.AuthorizationClient;
import com.demo.simplified_transaction.infrastructure.clients.NotificationClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NotificationService {

    private final NotificationClient client;

    public NotificationService(NotificationClient client) {
        this.client = client;
    }

    public void sendNotification() {
        client.sendNotification();
    }

}
