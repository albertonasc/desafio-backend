package com.demo.simplified_transaction.services;

import com.demo.simplified_transaction.infrastructure.entity.User;
import com.demo.simplified_transaction.infrastructure.exceptions.UserNotFound;
import com.demo.simplified_transaction.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Usuário não encontrado"));
    }

}
