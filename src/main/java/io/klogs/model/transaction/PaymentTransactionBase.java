package io.klogs.model.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;


public class PaymentTransactionBase {
    public BigDecimal amount = BigDecimal.ZERO;

    public TransactionType type;

    public TransactionStatus status;

    public Instant createdAtUtc;

    public ArrayList<TransactionError> errors;
}
