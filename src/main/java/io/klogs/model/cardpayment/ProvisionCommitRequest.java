package io.klogs.model.cardpayment;

import java.math.BigDecimal;

public class ProvisionCommitRequest {
    public String referenceCode;
    public BigDecimal amount = BigDecimal.ZERO;
}
