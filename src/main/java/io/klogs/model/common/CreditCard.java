package io.klogs.model.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreditCard {
    protected String cardHolderName;
    protected String cardNumber;
    protected String cvv;
    protected int expireYear;
    protected int expireMonth;
}
