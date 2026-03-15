package io.klogs.model.cardpayment;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Data
@SuperBuilder
public class CommissionRequest {
    protected String binNumber;
    protected BigDecimal amount;
    protected String currency;
    protected ArrayList<String> productCodes;
    protected ArrayList<String> productCategoryCodes;
    protected UUID cardId;
}
