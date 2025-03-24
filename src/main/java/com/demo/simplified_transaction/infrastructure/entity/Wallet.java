package com.demo.simplified_transaction.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "wallet")
@Table(name = "tb_wallet")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(name = "balance")
    private BigDecimal balance;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;

    public Wallet() {
    }

    public Wallet(Long walletId, BigDecimal balance, User user) {
        this.walletId = walletId;
        this.balance = balance;
        this.user = user;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
