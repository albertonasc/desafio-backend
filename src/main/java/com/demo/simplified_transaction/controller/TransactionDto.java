package com.demo.simplified_transaction.controller;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal value, Long payer, Long payee) {
}
