package io.klogs.model.cardpayment;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
public class CommissionRequest {
    protected String binNumber;
    protected BigDecimal amount;
    protected String currency;
}
