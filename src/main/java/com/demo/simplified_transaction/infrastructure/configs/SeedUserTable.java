package com.demo.simplified_transaction.infrastructure.configs;

import com.demo.simplified_transaction.infrastructure.entity.User;
import com.demo.simplified_transaction.infrastructure.entity.UserType;
import com.demo.simplified_transaction.infrastructure.entity.Wallet;
import com.demo.simplified_transaction.infrastructure.repository.UserRepository;
import com.demo.simplified_transaction.infrastructure.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class SeedUserTable {

    @Bean
    CommandLineRunner seedDatabase(UserRepository userRepository, WalletRepository walletRepository) {
        return args -> {
            if(userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User user1 = new User(null, "Carlos Silva", "carlos@email.com", "101111111111",
                        encoder.encode("123456"), null, UserType.COMMON);

                User user2 = new User(null, "Ana Souza", "ana@email.com", "22222222222",
                        encoder.encode("123456"), null, UserType.COMMON);

                User merchant = new User(null, "Loja Exemplo", "loja@email.com", "33333333333",
                        encoder.encode("123456"), null, UserType.MERCHANT);

                userRepository.saveAll(List.of(user1, user2, merchant));

                Wallet wallet1 = new Wallet(null, new BigDecimal("1000.00"), user1);
                Wallet wallet2 = new Wallet(null, new BigDecimal("2000.00"), user1);
                Wallet wallet3 = new Wallet(null, new BigDecimal("5000.00"), merchant);

                walletRepository.saveAll(List.of(wallet1, wallet2, wallet3));

                System.out.println("Usuários e carteiras populados com sucesso!");

            }
        };
    }

}
