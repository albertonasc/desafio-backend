package com.demo.simplified_transaction.controller.dto;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal value, Long payer, Long payee) {
}
