package com.demo.simplified_transaction.services;

import com.demo.simplified_transaction.controller.TransactionDto;
import com.demo.simplified_transaction.infrastructure.entity.Transaction;
import com.demo.simplified_transaction.infrastructure.entity.User;
import com.demo.simplified_transaction.infrastructure.entity.UserType;
import com.demo.simplified_transaction.infrastructure.exceptions.BadRequestException;
import com.demo.simplified_transaction.infrastructure.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final TransactionRepository transactionRepository;
    private final NotificationService notificationService;

    public TransactionService(UserService userService, AuthorizationService authorizationService, WalletService walletService, TransactionRepository transactionRepository, NotificationService notificationService) {
        this.userService = userService;
        this.authorizationService = authorizationService;
        this.walletService = walletService;
        this.transactionRepository = transactionRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public void transactionValues(TransactionDto transactionDto) {
       User payer =  userService.findUserById(transactionDto.payer());
       User payee =  userService.findUserById(transactionDto.payee());

       validatePayerMerchant(payer);
       validateUserBalance(payer, transactionDto.value());
       transactionValidate();

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transactionDto.value()));
        walletService.save(payer.getWallet());

        payee.getWallet().setBalance(payer.getWallet().getBalance().add(transactionDto.value()));
        walletService.save(payee.getWallet());

        Transaction transaction = createTransaction(transactionDto, payer, payee);

        transactionRepository.save(transaction);

        sendNotification();
    }

    private Transaction createTransaction(TransactionDto transactionDto, User payer, User payee) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.value());
        transaction.setSender(payer);
        transaction.setReceiver(payee);

        return transaction;
    }

    private void validatePayerMerchant(User user) {
        try {
            if(user.getUserType().equals(UserType.MERCHANT)) {
                throw new IllegalArgumentException("Transação não autorizada para este tipo de usuário");
            }
        } catch (Exception e) {
            throw  new IllegalArgumentException(e.getMessage());
        }
    }

    private void validateUserBalance(User user, BigDecimal value) {
        try {
            if(user.getWallet().getBalance().compareTo(value) < 0) {
                throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
            }
        } catch (Exception e) {
            throw  new IllegalArgumentException(e.getMessage());
        }
    }

    private void transactionValidate() {
        try {
            if(!authorizationService.transactionValidate()) {
                throw new IllegalArgumentException("Transação não autorizada pela API");
            }
        } catch (Exception e) {
            throw  new IllegalArgumentException(e.getMessage());
        }
    }

    private void sendNotification() {
        try {
            notificationService.sendNotification();
        } catch (HttpClientErrorException e) {
            throw  new BadRequestException("Erro ao enviar notificação");
        }
    }

}
