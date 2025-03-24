package com.demo.simplified_transaction.services;

import com.demo.simplified_transaction.infrastructure.entity.Wallet;
import com.demo.simplified_transaction.infrastructure.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
