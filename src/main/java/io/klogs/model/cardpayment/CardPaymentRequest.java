package io.klogs.model.cardpayment;

import io.klogs.model.common.Address;
import io.klogs.model.common.CreditCard;
import io.klogs.model.common.Product;
import io.klogs.model.common.Reward;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
@SuperBuilder
public class CardPaymentRequest {
    protected String token;

    @Builder.Default
    protected BigDecimal amount = BigDecimal.ZERO;
    protected Integer installment;
    protected String referenceCode;
    protected boolean useStoredCard;

    protected CreditCard card;
    protected Reward reward;
    protected Address invoice;
    protected Address shipping;

    protected String explanation;
    protected boolean use3d;

    protected Map<String, String> additionalData;

    protected String currency;
    protected String email;
    protected String phone;
    protected String returnURL;
    protected UUID paymentSystemId;
    protected String nationalNumber;

    protected Product[] products;
}
