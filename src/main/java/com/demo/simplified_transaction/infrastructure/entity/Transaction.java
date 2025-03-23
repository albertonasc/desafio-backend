package com.demo.simplified_transaction.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private BigDecimal amount;

    @JoinColumn(name = "sender_id")
    @ManyToOne
    private User sender;

    @JoinColumn(name = "receiver_id")
    @ManyToOne
    private User receiver;

    private LocalDateTime timestamp;

    @PrePersist
    void prePersist() {
        timestamp = LocalDateTime.now();
    }

}
