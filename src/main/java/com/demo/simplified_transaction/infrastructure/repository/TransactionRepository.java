package com.demo.simplified_transaction.infrastructure.repository;

import com.demo.simplified_transaction.infrastructure.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
