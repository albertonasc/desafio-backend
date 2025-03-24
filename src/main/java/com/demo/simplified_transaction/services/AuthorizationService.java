package com.demo.simplified_transaction.services;

import com.demo.simplified_transaction.infrastructure.clients.AuthorizationClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final AuthorizationClient client;

    public AuthorizationService(AuthorizationClient client) {
        this.client = client;
    }

    public boolean transactionValidate() {
        if(Objects.equals(client.authorizationValidate().data().authorization(), "true")) {
            return true;
        }

        return false;
    }

}
