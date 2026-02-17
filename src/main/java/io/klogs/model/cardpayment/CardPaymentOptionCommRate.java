package io.klogs.model.cardpayment;

import io.klogs.model.common.CommApplyType;

import java.math.BigDecimal;
import java.util.UUID;

public class CardPaymentOptionCommRate {
    public BigDecimal commRate;
    public int installment;
    public String paymentProviderId;
    public UUID paymentSystemId;
    public Integer plusInstallment;
    public Integer paymentDeferral;
    public CommApplyType commApplyType;
    public BigDecimal paidAmount;
    public BigDecimal netAmount;
    public String cardProgram;
}
