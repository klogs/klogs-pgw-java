package io.klogs.model.hostedpayment;

import io.klogs.model.cardpayment.SalesType;
import io.klogs.model.common.Address;
import io.klogs.model.common.Product;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Map;

@Data
@SuperBuilder
public class HostedPaymentRequest {
    @Builder.Default
    protected BigDecimal amount = BigDecimal.ZERO;
    protected String currency;

    protected String referenceCode;

    protected Address invoice;
    protected Address shipping;

    protected String explanation;

    protected Map<String, String> additionalData;

    protected String email;
    protected String phone;
    protected String returnURL;
    protected SalesType chargeType;
    protected String fullName;
    protected String nationalNumber;

    protected Product[] products;
    protected String paymentMethod;
}
